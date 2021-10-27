<template>
  <div id="app">
    <el-container>
      <el-header>
        <el-menu
          :default-active="activeIndex"
          class="el-menu-demo"
          mode="horizontal"
          :router="true"
        >
          <el-menu-item v-if="pc">
            <el-image
              class="logo"
              :src="require('../../assets/logo.png')"
              fit="cover"
            ></el-image>
          </el-menu-item>
          <el-menu-item index="/">首页</el-menu-item>
          <el-menu-item index="/history">测速记录</el-menu-item>
          <el-submenu class="user" index="">
            <template slot="title">{{ username }}</template>
            <el-menu-item>
              <el-link
                type="danger"
                :underline="false"
                @click="logout"
                >退出登录</el-link
              >
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-header>
      <el-main class="main">
        <router-view></router-view>
      </el-main>
      <el-footer> </el-footer>
    </el-container>
  </div>
</template>

<script>
import axios from "axios";
import { ApiHost } from "../../utils/api";
export default {
  name: "App",
  methods: {
    logout: function() {
      if (this.appData.ENABLE_CAS_LOGIN) {
        window.location.href = this.appData.CAS_CENTER_ADDRESS + this.appData.CAS_LOGOUT_PATH;
      }
      axios.delete(ApiHost + '/api/user/session').then((rep) => {
        let responseData = rep.data;
        if (responseData.status) {
          window.location.href = '/';
        }
        else {
          this.$message.error(responseData.data.message);
        }
      }).catch((error) => {
        console.error(error);
        this.$message.error("退出登录失败");
      })
    },
    resize: function() {
      this.pc = this.screenWidth > 600;
    }
  },
  mounted: function () {
    axios
      .get(ApiHost + "/api/config/appinfo")
      .then((rep) => {
        let reponseData = rep.data;
        if (reponseData.status) {
          this.appData = reponseData.data;
          if (this.appData.WEBAPP_NAME) {
            document.title = this.appData.WEBAPP_NAME;
          }
        } else {
          this.$message.error(reponseData.data.message);
        }
      })
      .catch((error) => {
        console.error(error);
        this.$message.error("应用信息加载失败");
      });

    axios
      .get(ApiHost + "/api/user/logined")
      .then((rep) => {
        let reponseData = rep.data;
        if (reponseData.status) {
          if (reponseData.data.username) {
            this.username = reponseData.data.username;
          }
        } else {
          this.$message.error(reponseData.data.message);
        }
      })
      .catch((error) => {
        console.error(error);
        this.$message.error("用户信息加载失败");
      });

      window.onresize = () => {
        if (this.widthTimer) {
          return;
        }
        this.screenWidth = document.body.clientWidth;
        this.resize();
        this.widthTimer = setTimeout(() => {
          this.widthTimer = undefined;
        }, 200);
      }
  },
  data: () => {
    let screenWidth = document.body.clientWidth;
    return {
      activeIndex: "/",
      title: "",
      appData: {},
      username: "请登录",
      screenWidth: screenWidth,
      widthTimer: undefined,
      pc: true
    };
  },
  components: {},
};
</script>
<style scoped>
html, body {
  font-size: 8px;
}
.logo {
  width: 30px;
  height: 30px;
}
.user {
  float: right !important;
}
</style>
