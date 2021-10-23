<template>
  <div id="app">
    <el-row type="flex" justify="space-around">
      <el-col :sm="18" :lg="6">
        <el-form :model="inputForm" ref="inputForm" :rules="rules">
          <el-form-item label="用户名:" prop="username">
            <el-input
              v-model="inputForm.username"
              placeholder="用户名"
            ></el-input>
          </el-form-item>
          <el-form-item label="密码:" prop="password">
            <el-input
              v-model="inputForm.password"
              placeholder="密码"
              show-password
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('inputForm')"
              >登录</el-button
            >
            <el-link type="primary" href="/register" :underline="false" class="link"
              >去注册</el-link
            >
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import { ApiHost } from "../../utils/api";
import axios from "axios";
export default {
  name: "app",
  data: () => {
    return {
      inputForm: {
        username: "",
        password: "",
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 6, max: 16, message: "长度在6~16之间", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 16, message: "长度在6~16之间", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    submitForm: function (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.login();
        } else {
          return false;
        }
      });
    },
    login: function () {
      let formData = new FormData();
      formData.append("username", this.inputForm.username);
      formData.append("password", this.inputForm.password);
      axios
        .post(ApiHost + "/api/user/session?setCookie=true", formData)
        .then((rep) => {
          let reponseData = rep.data;
          if (reponseData.status) {
            window.location.href = "/";
          } else {
            this.$message.error(reponseData.data.message);
          }
        })
        .catch((error) => {
          console.error(error);
          this.$message.error("登录失败");
        });
    },
  },
};
</script>
<style scoped>
.link {
    margin-left: 20px;
}
</style>