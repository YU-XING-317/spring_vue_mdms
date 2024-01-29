<template>
  <div class="wrapper">
    <div class="table-control">
      <el-form :inline="true" :model="formQuery" class="form-query">
        <el-form-item label="设备名称">
          <el-input v-model="formQuery.dname"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="tableSearch">搜索</el-button>
          <el-button @click="resetSearch">重置</el-button>
          <el-button @click="addMRecordVisible = true">增加维保记录</el-button>
        </el-form-item>
      </el-form>
      <el-dialog v-model="addMRecordVisible" title="增加维保记录">
        <el-form :model="addMRecord" class="add-mrecord" size="small">
          <el-form-item label="维保设备注册编号">
            <el-input v-model="addMRecord.did"></el-input>
          </el-form-item>
          <el-form-item label="维保人员编号">
            <el-input v-model="addMRecord.eid"></el-input>
          </el-form-item>
          <el-form-item label="维保时间">
            <el-date-picker
              v-model="addMRecord.mrTime"
              palceholder="选择一个时间"
              range-separator="-"
              type="date"
              value-format="YYYY-MM-DD"
            ></el-date-picker>
          </el-form-item>
          <el-form-item label="维保项">
            <el-input v-model="addMRecord.mrContent"></el-input>
          </el-form-item>
          <el-form-item label="维保单位">
            <el-input v-model="addMRecord.mrEntity"></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-select v-model="addMRecord.mrState">
              <el-option label="未开始" value="未开始" />
              <el-option label="进行中" value="进行中" />
              <el-option label="已完成" value="已完成" />
            </el-select>
          </el-form-item>
          <el-form-item label="是否超期未保">
            <el-radio-group v-model="addMRecord.mrExp">
              <el-radio label="是" value="是"></el-radio>
              <el-radio label="否" value="否"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="备注">
            <el-input
              v-model="addMRecord.mrMark"
              placeholder="不超过20个字"
              type="textarea"
            ></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="addMRecordVisible = false">取消</el-button>
            <el-button type="primary" @click="addMRecordNoId"> 确认 </el-button>
          </span>
        </template>
      </el-dialog>
    </div>
    <el-table :data="mrecord" class="table" height="88%" stripe>
      <el-table-column type="expand">
        <template #default="props">
          <p style="margin: 10px; text-align: left">
            <b>备注: </b>&nbsp;
            {{ props.row.mrMark == "" ? "无" : props.row.mrMark }}
          </p>
        </template>
      </el-table-column>
      <el-table-column label="维保时间" prop="mrTime"></el-table-column>
      <el-table-column label="维保项" prop="mrContent"></el-table-column>
      <el-table-column label="维保单位" prop="mrEntity"></el-table-column>
      <el-table-column label="是否到期未保" prop="mrExp"></el-table-column>
      <el-table-column label="状态" prop="mrState"></el-table-column>
      <el-table-column label="维保人员" prop="employer.ename"></el-table-column>
      <el-table-column label="维保设备" prop="device.dname"></el-table-column>
      <el-table-column align="center" label="操作">
        <template #default="scope">
          <el-button
            size="small"
            type="primary"
            @click="reviseFormVisible = true"
            >修改</el-button
          >
          <el-dialog v-model="reviseFormVisible" title="修改记录">
            <el-form :model="reviseForm">
              <el-form-item label="状态">
                <el-select v-model="reviseForm.mrState">
                  <el-option label="未开始" value="未开始" />
                  <el-option label="进行中" value="进行中" />
                  <el-option label="已完成" value="已完成" />
                </el-select>
              </el-form-item>
              <el-form-item label="是否超期未保">
                <el-radio-group v-model="reviseForm.mrExp">
                  <el-radio label="是" value="是"></el-radio>
                  <el-radio label="否" value="否"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="备注">
                <el-input
                  v-model="reviseForm.mrMark"
                  placeholder="不超过20个字"
                  type="textarea"
                ></el-input>
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
        dname: "",
      },
      addMRecordVisible: false,
      addMRecord: {
        did: "",
        eid: "",
        mrTime: "",
        mrEntity: "",
        mrContent: "",
        mrState: "未开始",
        mrExp: "是",
        mrMark: "",
      },
      reviseFormVisible: false,
      reviseForm: {
        mrState: "未开始",
        mrExp: "是",
        mrMark: "",
      },
      mrecord: [],
    });

    init();
    function init() {
      axios
        .post("maintenanceRecord/getMaintenanceRecordByDName", {
          dname: state.formQuery.dname,
        })
        .then((response) => {
          state.mrecord = response.data;
          console.log(state.mrecord);
        })
        .catch((error) => {
          console.error(error);
        });
    }

    // 增加维保记录，无did
    const addMRecordNoId = () => {
      if (
        state.addMRecord.did == "" ||
        state.addMRecord.eid == "" ||
        state.addMRecord.mrTime == null ||
        state.addMRecord.mrTime == "" ||
        state.addMRecord.mrContent == null ||
        state.addMRecord.mrContent == "" ||
        state.addMRecord.mrEntity == "" ||
        state.addMRecord.mrState == "" ||
        state.addMRecord.mrExp == ""
      ) {
        ElMessage.warning("请完整填写维保记录信息！");
        return;
      }

      // 将设备ID和员工ID传到后端
      // 增加一条维保记录
      axios
        .post("maintenanceRecord/insertMaintenanceRecord", {
          did: state.addMRecord.did,
          eid: state.addMRecord.eid,
          mrTime: state.addMRecord.mrTime,
          mrContent: state.addMRecord.mrContent,
          mrEntity: state.addMRecord.mrEntity,
          mrState: state.addMRecord.mrState,
          mrExp: state.addMRecord.mrExp,
          mrMark: state.addMRecord.mrMark,
        })
        .then((response) => {
          if (response.data.includes("错误")) {
            ElMessage.error(response.data);
            return;
          } else {
            ElMessage.success(response.data);
          }
          addMRecordVisible = false;
        })
        .catch((error) => {
          console.error(error);
        });
    };
    // 根据设备Id修改维保记录
    const handleRevise = (mrecord) => {
      if (
        state.reviseForm.mrState == "" ||
        state.reviseForm.mrExp == "" ||
        state.reviseForm.mrMark == ""
      ) {
        ElMessage.warning("请输入修改类型！");
        return;
      }
      axios
        .post("maintenanceRecord/maintenanceRecord", {
          mrId: mrecord.mrId,
          mrTime: mrecord.mrTime,
          mrContent: mrecord.mrTime,
          mrEntity: mrecord.mrEntity,
          mrState: state.reviseForm.mrState,
          mrExp: state.reviseForm.mrExp,
          mrMark: state.reviseForm.mrMark,
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
    };

    const tableSearch = () => {
      init();
    };

    const resetSearch = () => {
      state.formQuery.dname = "";
    };
    return {
      ...toRefs(state),
      handleRevise,
      tableSearch,
      resetSearch,
      addMRecordNoId,
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

.table {
  border-radius: 6px;
  box-shadow: 5px 3px 3px grey;
}
</style>
