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
          <el-menu-item>
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
            <el-menu-item><el-link type="danger" href="/loggout" :underline="false">退出登录</el-link></el-menu-item>
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
  },
  data: () => {
    return {
      activeIndex: "/",
      title: "xss",
      appData: {},
      username: "请登录",
    };
  },
  components: {},
};
</script>
<style scoped>
.logo {
  width: 30px;
  height: 30px;
}
.user {
  float: right !important;
}
</style>
