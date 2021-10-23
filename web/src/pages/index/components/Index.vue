<template>
  <Speedtest class="speedtest"></Speedtest>
</template>
<script>
import Speedtest from "./Speedtest/Speedtest.vue";
export default {
  name: "Index",
  components: {
    Speedtest,
  },
};
</script>
<style scoped>
.speedtest {
  max-width: 600px;
  margin: 20px auto;
}
</style>