<template>
  <div>
    <el-row>
      <el-col>
        <el-table :data="historyData" border>
          <el-table-column prop="id" label="Id"></el-table-column>
          <el-table-column prop="time" label="时间"></el-table-column>
          <el-table-column prop="ip" label="ip"></el-table-column>
          <el-table-column
            prop="extraAttribute.position"
            label="接入地点"
          ></el-table-column>
          <el-table-column
            prop="extraAttribute.accessMethod"
            label="接入方式"
          ></el-table-column>
          <el-table-column prop="dl" label="下载速度/Mbps"></el-table-column>
          <el-table-column prop="ul" label="上传速度/Mbps"></el-table-column>
          <el-table-column prop="ping" label="ping/ms"></el-table-column>
          <el-table-column prop="jitter" label="jitter/ms"> </el-table-column>
          <el-table-column prop="testPointName" label="测速节点名称">
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <el-row class="page-row">
      <el-col :span="24">
        <el-pagination
          background
          layout="total, sizes, prev, pager, next, jumper"
          :page-sizes="[5, 10, 25, 50]"
          :total="historyDataCount"
          class="page"
          :page-size="pageSize"
          @current-change="pageChange"
          @size-change="sizeChange"
        >
        </el-pagination>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import axios from "axios";
import { ApiHost } from "../../../utils/api";
export default {
  name: "history",
  data: () => {
    return {
      historyData: [],
      historyDataCount: 20,
      pageSize: 10,
      currentPage: 1
    };
  },
  methods: {
    pageChange: function (page) {
        this.currentPage = page;
      axios
        .get(ApiHost + "/api/history/user", {
          params: {
            index: page,
            size: this.pageSize,
          },
        })
        .then((rep) => {
          let responseData = rep.data;
          if (responseData.status) {
            this.historyDataCount = responseData.data.count;
            this.historyData = responseData.data.list;
          } else {
            this.$message.error(responseData.data.message);
          }
        })
        .catch((error) => {
          console.error(error);
          this.$message.error("历史数据加载失败");
        });
    },
    sizeChange: function(size) {
        this.pageSize = size;
        this.pageChange(this.currentPage);
    }
  },
  mounted: function () {
    this.pageChange(1);
  },
};
</script>
<style scoped>
.page {
  text-align: center;
}
.page-row {
  margin-top: 20px;
}
</style>