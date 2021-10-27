<template>
  <div id="testWrapper">
    <el-row type="flex" justify="space-around">
      <el-col :span="6">
        <el-button
          class="startButton"
          :type="startButtonType"
          @click="startStop"
          :disabled="startButtonForbidden"
          >{{ !startButtonStart ? "开始" : "结束" }}</el-button
        >
      </el-col>
    </el-row>
    <el-row class="serverListArea" type="flex" justify="space-around">
      <el-col :span="16">
        <el-form>
          <el-form-item label="测速节点列表:">
            <el-select
              placeholder="请选择"
              v-model="changeServerId"
              @change="changeServer"
            >
              <el-option
                v-for="item in serverLists"
                :key="item.id"
                :value="item.id"
                :label="item.name"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <el-row class="resNumberArea" type="flex" justify="space-around">
      <el-col :span="9">
        <div class="testName">Ping</div>
        <div id="pingText" class="meterText" style="color: #aa6060">
          {{ pingText }} ms
        </div>
      </el-col>
      <el-col :span="9">
        <div class="testName">Jitter</div>
        <div id="jitText" class="meterText" style="color: #aa6060">
          {{ jitterText }} ms
        </div>
      </el-col>
    </el-row>
    <el-row class="resNumberArea" type="flex" justify="space-around">
      <el-col class="testArea">
        <div class="testName">Download</div>
        <div class="picArea">
          <canvas id="dlMeter" class="meter"></canvas>
          <div id="dlText" class="meterText">{{ dlText }} Mbps</div>
        </div>
      </el-col>
      <el-col class="testArea">
        <div class="testName">Upload</div>
        <div class="picArea">
          <canvas id="ulMeter" class="meter"></canvas>
          <div id="ulText" class="meterText">{{ ulText }} Mbps</div>
        </div>
      </el-col>
    </el-row>
    <el-row type="flex" justify="space-around">
      <el-col id="ipArea">
        <div id="ipAndIsp">{{ ipAndIspContent }}</div>
        <div id="ipPositionAndAccessMethod">
          {{ ipPositionAndAccessMethodContent }}
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import Speedtest from "./speedtest";
import { ApiHost } from "../../../../utils/api";
export default {
  name: "Speedtest",
  data: function () {
    return {
      s: new Speedtest(),
      pointReady: false,
      uiData: null,
      startButtonType: "primary",
      startButtonForbidden: true,
      startButtonStart: false,
      ipAndIspContent: "",
      ipPositionAndAccessMethodContent: "",
      dlText: 0,
      ulText: 0,
      pingText: 0,
      jitterText: 0,
      serverLists: [],
      changeServerId: 1,
    };
  },
  methods: {
    startStop: function () {
      if (!this.pointReady) return;
      if (this.s.getState() == 3) {
        //speedtest is running, abort
        this.s.abort();
        // data = null;
        this.startButtonType = "primary";
        this.startButtonStart = false;
        this.initUI();
      } else {
        //test is not running, begin
        this.startButtonType = "danger";
        this.startButtonStart = true;
        this.s.onupdate = (data) => {
          this.uiData = data;
        };
        this.s.onend = () => {
          this.startButtonType = "primary";
          this.startButtonStart = false;
          this.updateUI(true);
        };
        this.s.start();
      }
    },
    tryStop: function () {
      if (this.s.getState() == 3) {
        //speedtest is running, abort
        this.s.abort();
        // data = null;
        this.startButtonType = "primary";
        this.startButtonStart = false;
        this.initUI();
      }
    },
    initUI: function () {
      var meterBk = /Trident.*rv:(\d+\.\d+)/i.test(navigator.userAgent)
        ? "#EAEAEA"
        : "#80808040";
      var dlColor = "#6060AA",
        ulColor = "#616161";
      this.drawMeter(this.I("dlMeter"), 0, meterBk, dlColor, 0);
      this.drawMeter(this.I("ulMeter"), 0, meterBk, ulColor, 0);
    },
    updateUI: function (forced) {
      var meterBk = /Trident.*rv:(\d+\.\d+)/i.test(navigator.userAgent)
        ? "#EAEAEA"
        : "#80808040";
      var dlColor = "#6060AA",
        ulColor = "#616161";
      var progColor = meterBk;

      if (!forced && this.s.getState() != 3) return;
      if (this.uiData == null) return;
      var status = this.uiData.testState;
      // 因为再调用abort之后不知道为啥该函数会再执行一次
      // 此时所有uiData已经为空，因此不需要显示链接符号
      let linkChar = "";
      if (this.uiData.clientIp.length && this.uiData.isp.length) linkChar = "-";
      this.ipAndIspContent = this.uiData.clientIp + linkChar + this.uiData.isp;
      if (this.uiData.ipPosition.length && this.uiData.ipAccessMethod.length) {
        linkChar = "-";
      } else {
        linkChar = "";
      }
      this.ipPositionAndAccessMethodContent =
        this.uiData.ipPosition + linkChar + this.uiData.ipAccessMethod;
      this.dlText =
        status == 1 && this.uiData.dlStatus == 0
          ? "..."
          : this.format(this.uiData.dlStatus);
      this.drawMeter(
        this.I("dlMeter"),
        this.mbpsToAmount(
          Number(this.uiData.dlStatus * (status == 1 ? this.oscillate() : 1))
        ),
        meterBk,
        dlColor,
        Number(this.uiData.dlProgress),
        progColor
      );
      this.ulText =
        status == 3 && this.uiData.ulStatus == 0
          ? "..."
          : this.format(this.uiData.ulStatus);
      this.drawMeter(
        this.I("ulMeter"),
        this.mbpsToAmount(
          Number(this.uiData.ulStatus * (status == 3 ? this.oscillate() : 1))
        ),
        meterBk,
        ulColor,
        Number(this.uiData.ulProgress),
        progColor
      );
      this.pingText = this.format(this.uiData.pingStatus);
      this.jitterText = this.format(this.uiData.jitterStatus);
    },
    I: (i) => {
      return document.getElementById(i);
    },
    mbpsToAmount: (s) => {
      return 1 - 1 / Math.pow(1.3, Math.sqrt(s));
    },
    format: (d) => {
      d = Number(d);
      if (d < 10) return d.toFixed(2);
      if (d < 100) return d.toFixed(1);
      return d.toFixed(0);
    },
    oscillate: () => {
      return 1 + 0.02 * Math.sin(Date.now() / 100);
    },
    drawMeter: (c, amount, bk, fg, progress, prog) => {
      if (!c) return;
      var ctx = c.getContext("2d");
      var dp = window.devicePixelRatio || 1;
      var cw = c.clientWidth * dp,
        ch = c.clientHeight * dp;
      var sizScale = ch * 0.0055;
      if (c.width == cw && c.height == ch) {
        ctx.clearRect(0, 0, cw, ch);
      } else {
        c.width = cw;
        c.height = ch;
      }
      ctx.beginPath();
      ctx.strokeStyle = bk;
      ctx.lineWidth = 12 * sizScale;
      ctx.arc(
        c.width / 2,
        c.height - 58 * sizScale,
        c.height / 1.8 - ctx.lineWidth,
        -Math.PI * 1.1,
        Math.PI * 0.1
      );
      ctx.stroke();
      ctx.beginPath();
      ctx.strokeStyle = fg;
      ctx.lineWidth = 12 * sizScale;
      ctx.arc(
        c.width / 2,
        c.height - 58 * sizScale,
        c.height / 1.8 - ctx.lineWidth,
        -Math.PI * 1.1,
        amount * Math.PI * 1.2 - Math.PI * 1.1
      );
      ctx.stroke();
      if (typeof progress !== "undefined") {
        ctx.fillStyle = prog;
        ctx.fillRect(
          c.width * 0.3,
          c.height - 16 * sizScale,
          c.width * 0.4 * progress,
          4 * sizScale
        );
      }
    },
    frame: function () {
      requestAnimationFrame(this.frame);
      this.updateUI();
    },
    changeServer: function (id) {
      id--;
      this.s.setSelectedServer(this.s._serverList[id]);
    },
  },
  mounted: function () {
    this.s.setParameter("telemetry_level", "basic");
    this.initUI();
    this.frame();
    this.s.loadServerList(ApiHost + "/api/testpoint?cors=1", (servers) => {
      if (servers == null) {
        this.changeServerId = "测速节点加载失败";
        this.$message.error("测速节点列表加载失败");
        return;
      }
      // default no auto server select
      this.changeServerId = servers[0].id;
      this.startButtonForbidden = false;
      this.s.setSelectedServer(servers[0]);
      // update ui
      this.serverLists = servers;
      this.pointReady = true;
      this.startButtonType = "primary";
    });
  }
};
</script>
<style scoped>
.startButton {
  width: 100%;
}
.serverListArea {
  margin-top: 20px;
}
.resNumberArea {
  text-align: center;
  margin-bottom: 20px;
}
.center {
  text-align: center;
}
.testArea {
  height: 220px;
}
.picArea {
  position: relative;
  height: 200px;
}
.picArea > * {
  position: absolute;
  left: 0px;
  right: 0px;
  margin: 0px auto;
}
.picArea > canvas {
  top: 0px;
  bottom: 0px;
  width: 100%;
  height: 100%;
}
#dlText,
#ulText {
  height: 30px;
  line-height: 30px;
  top: 50%;
}

#dlText,
#ulText,
#pingText,
#jitText {
  font-weight: bold;
  font-size: 1.4em;
}
.testName {
  font-size: 1.8em;
}
#ipArea > div {
  text-align: center;
}
</style>
