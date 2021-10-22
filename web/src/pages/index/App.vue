<template>
  <div id="app">
    <el-container>
      <el-header>
        <el-menu
          :default-active="activeIndex"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
        >
          <el-menu-item index="1">首页</el-menu-item>
          <el-menu-item index="2">测速记录</el-menu-item>
        </el-menu>
      </el-header>
      <el-main class="main">
        <Speedtest class="speedtest"></Speedtest>
      </el-main>
      <el-footer>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import Speedtest from "./components/Speedtest/Speedtest.vue";
export default {
  name: "App",
  components: {
    Speedtest,
  },
};
</script>

<style scoped>
.speedtest {
  max-width: 600px;
  margin:0px auto;
}
.main {
  text-align: center;
}
</style>
