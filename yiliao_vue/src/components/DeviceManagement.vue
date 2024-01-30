<template>
  <div class="wrapper">
    <div class="table-control">
      <el-form :inline="true" :model="formQuery" class="form-query">
        <el-form-item label="设备名称" size="small">
          <el-input v-model="formQuery.dname"></el-input>
        </el-form-item>
        <el-form-item label="供应商" size="small">
          <el-input v-model="formQuery.supplier"></el-input>
        </el-form-item>
        <el-form-item label="类型" size="small">
          <el-select
            v-model="formQuery.dtype"
            clearable
            placeholder="全部"
            style="width: 30vh"
          >
            <el-option label="销售" value="销售" />
            <el-option label="租赁" value="租赁" />
            <el-option label="折扣" value="折扣" />
            <el-option label="新产品" value="新产品" />
          </el-select>
        </el-form-item>
        <el-form-item size="small">
          <el-button type="primary" @click="tableSearch">搜索</el-button>
          <el-button @click="resetSearch">重置</el-button>
          <el-button @click="addDeviceVisible = true">增加设备</el-button>
        </el-form-item>
      </el-form>
      <el-dialog v-model="addDeviceVisible" title="增加设备信息">
        <el-form :model="addDevice" class="add-device" label-position="left">
          <el-form-item label="设备名">
            <el-input v-model="addDevice.dname"></el-input>
          </el-form-item>
          <el-form-item label="供应商">
            <el-input v-model="addDevice.supplier"></el-input>
          </el-form-item>
          <el-form-item label="类型">
            <el-select v-model="addDevice.dtype">
              <el-option label="销售" value="销售" />
              <el-option label="租赁" value="租赁" />
              <el-option label="折扣" value="折扣" />
              <el-option label="新产品" value="新产品" />
            </el-select>
          </el-form-item>
          <el-form-item label="医院">
            <el-input v-model="addDevice.hospital"></el-input>
          </el-form-item>
          <el-form-item label="科室">
            <el-input v-model="addDevice.department"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="addDeviceVisible = false">取消</el-button>
            <el-button type="primary" @click="addDeviceNoId"> 确认 </el-button>
          </span>
        </template>
      </el-dialog>
    </div>
    <el-table :data="device" height="88%" stripe>
      <el-table-column label="设备注册编号" prop="did"></el-table-column>
      <el-table-column label="供应商名称" prop="supplier"></el-table-column>
      <el-table-column label="设备名称" prop="dname"></el-table-column>
      <el-table-column label="类型" prop="dtype"></el-table-column>
      <el-table-column label="医院" prop="hospital"></el-table-column>
      <el-table-column label="科室" prop="department"></el-table-column>
      <el-table-column align="center" label="操作">
        <template #default="scope">
          <el-button
            size="small"
            type="primary"
            @click="reviseFormVisible = true"
            >修改</el-button
          >
          <el-dialog v-model="reviseFormVisible" title="修改产品">
            <el-form :model="reviseForm">
              <el-form-item label="类型">
                <el-input v-model="reviseForm.dtype"></el-input>
              </el-form-item>
            </el-form>
            <template #footer>
              <span class="dialog-footer">
                <el-button @click="reviseFormVisible = false">取消</el-button>
                <el-button type="primary" @click="handleRevise(scope.row)">
                  确认
                </el-button>
              </span>
            </template>
          </el-dialog>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {reactive, toRefs} from "vue";
import {ElMessage} from "element-plus";
import axios from "axios";

axios.defaults.baseURL = "http://localhost:8080/yiliao";

export default {
  setup() {
    const state = reactive({
      formQuery: {
        dtype: "",
        dname: "",
        supplier: "",
      },
      addDeviceVisible: false,
      addDevice: {
        dname: "",
        dtype: "新产品",
        supplier: "",
        hospital: "",
        department: "",
      },
      reviseFormVisible: false,
      reviseForm: {
        dtype: "",
      },
      device: [],
    });

    function init() {
      axios
        .get("device/getDeviceByKeys", {
          dname: state.formQuery.dname,
          dtype: state.formQuery.dtype,
          supplier: state.formQuery.supplier,
        })
        .then((response) => {
          state.device = response.data;
          console.log(state.device);
        })
        .catch((error) => {
          console.error(error);
        });
    }
    init();

    // 增加设备信息，无did
    function addDeviceNoId() {
      if (
        state.addDevice.dname == "" ||
        state.addDevice.dtype == "" ||
        state.addDevice.supplier == "" ||
        state.addDevice.hospital == "" ||
        state.addDevice.department == ""
      ) {
        ElMessage({ message: "请完整填写设备信息！", type: "warning" });
        console.log("fd");
        return;
      }
      axios
        .post("device/insertDevice", {
          dname: state.addDevice.dname,
          dtype: state.addDevice.dtype,
          supplier: state.addDevice.supplier,
          hospital: state.addDevice.hospital,
          department: state.addDevice.department,
        })
        .catch((error) => {
          console.error(error);
        });
    }
    // 根据设备Id修改设备信息
    function handleRevise(device) {
      if (dtype == "") {
        ElMessage.warning("请输入修改类型！");
        return;
      }
      axios
        .post("device/updateDevice", {
          did: device.did,
          dname: device.dname,
          dtype: state.reviseForm.dtype,
          supplier: device.supplier,
          hospital: device.hospital,
          department: device.department,
        })
        .then((response) => {
          if (response.data > 0) {
            ElMessage.success("修改成功！");
            init();
          } else {
            ElMessage.error("修改失败！");
          }
        })
        .catch((error) => {
          console.error(error);
        });
      state.reviseFormVisible = false;
      console.log(device);
    }

    function tableSearch() {
      init();
    }

    function resetSearch() {
      state.formQuery.dtype = "";
      state.formQuery.dname = "";
      state.formQuery.supplier = "";
    }
    return {
      ...toRefs(state),
      handleRevise,
      tableSearch,
      resetSearch,
      addDeviceNoId,
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
