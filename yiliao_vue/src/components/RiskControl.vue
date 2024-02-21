<template>
  <div class="wrapper">
    <div class="table-control">
      <p>设备风险预警</p>
    </div>
    <el-table :data="device" height="88%" stripe>
      <el-table-column label="设备注册编号" prop="did"></el-table-column>
      <el-table-column label="维保状态" prop="deviceState">
        <template v-slot="scope">
          <p v-if="scope.row.deviceState=='0'">未开始</p>
          <p v-else-if="scope.row.deviceState=='1'">进行中</p>
          <p v-else-if="scope.row.deviceState=='2'">已完成</p>
        </template>
      </el-table-column>
      <el-table-column label="是否超期未保" prop="isOverdue">
        <template v-slot="scope">
          <p v-if="scope.row.isOverdue=='0'">未超期</p>
          <p v-else-if="scope.row.isOverdue=='1'">已超期</p>
        </template>
      </el-table-column>
      <el-table-column label="检验状态" prop="isChecked">
        <template v-slot="scope">
          <p v-if="scope.row.isChecked=='0'">未检验</p>
          <p v-else-if="scope.row.isChecked=='1'">已检验</p>
        </template>
      </el-table-column>
      <el-table-column label="检验结果" prop="checkResult">
        <template v-slot="scope">
          <p v-if="scope.row.checkResult=='1'">合格</p>
          <p v-else-if="scope.row.checkResult=='2'">不合格</p>
        </template>
      </el-table-column>
      <el-table-column label="设备风险状态" prop="isRisk">
        <template v-slot="scope">
          <p v-if="scope.row.isRisk=='0'">无风险</p>
          <p v-else-if="scope.row.isRisk=='1'">有风险</p>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {reactive, toRefs} from "vue";
import axios from "axios";

axios.defaults.baseURL = "http://localhost:8080/yiliao";

export default {
  setup() {
    const state = reactive({
      formQuery: {
        did: "",
        maintenanceStatus: "",
        isOverdue: "",
        inspectionStatus: "",
        inspectionResult: "",
        riskStatus: "",
      },


      device: [],
    });

    function init() {
      axios({
        url: "/mRisk",
        params: {
        },
        method:"get"
      }).then((response) => {
        state.device = response.data;
        console.log(state.device);
      }).catch((error) => {
        console.error(error);
      });
    }
    init();




    return {
      ...toRefs(state),
    };
  },
};
</script>

<style scoped>
.wrapper {
  height: 83vh;
}

.table-control {
  height: 15%;
}
</style>
