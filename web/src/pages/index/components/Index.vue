<template>
  <Speedtest class="speedtest" ref="Speedtest"></Speedtest>
</template>
<script>
import Speedtest from "./Speedtest/Speedtest.vue";
export default {
  name: "Index",
  components: {
    Speedtest,
  },
  beforeRouteLeave : function (to, from, next) {
    this.$refs.Speedtest.tryStop();
    next();
  },
};
</script>
<style scoped>
.speedtest {
  max-width: 600px;
  margin: 20px auto;
}
</style>