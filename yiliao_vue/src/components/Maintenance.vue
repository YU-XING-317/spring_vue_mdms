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
              v-model="addMRecord.mrtime"
              palceholder="选择一个时间"
              range-separator="-"
              type="date"
              value-format="YYYY-MM-DD"
            ></el-date-picker>
          </el-form-item>
          <el-form-item label="维保项">
            <el-input v-model="addMRecord.mrcontent"></el-input>
          </el-form-item>
          <el-form-item label="维保单位">
            <el-input v-model="addMRecord.mrentity"></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-select v-model="addMRecord.mrstate">
              <el-option label="未开始" value="未开始" />
              <el-option label="进行中" value="进行中" />
              <el-option label="已完成" value="已完成" />
            </el-select>
          </el-form-item>
          <el-form-item label="是否超期未保">
            <el-radio-group v-model="addMRecord.mrexp">
              <el-radio label="是" value="是"></el-radio>
              <el-radio label="否" value="否"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="备注">
            <el-input
              v-model="addMRecord.mrmakr"
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
            {{ props.row.mrmakr == "" ? "无" : props.row.mrmakr }}
          </p>
        </template>
      </el-table-column>
      <el-table-column label="维保时间" prop="mrtime"></el-table-column>
      <el-table-column label="维保项" prop="mrcontent"></el-table-column>
      <el-table-column label="维保单位" prop="mrentity"></el-table-column>
      <el-table-column label="是否到期未保" prop="mrexp"></el-table-column>
      <el-table-column label="状态" prop="mrstate"></el-table-column>
      <el-table-column label="维保人员" prop="employer.ename"></el-table-column>
      <el-table-column label="维保设备" prop="device.dname"></el-table-column>
      <el-table-column align="center" label="操作">
        <template #default="scope">
          <el-button
            size="small"
            type="primary"
            @click="getRowReviseData(scope.row)"
            >修改</el-button
          >
          <el-dialog v-model="reviseFormVisible" title="修改记录">
            <el-form :model="reviseForm">
              <el-form-item label="状态">
                <el-select v-model="reviseForm.mrstate">
                  <el-option label="未开始" value="未开始" />
                  <el-option label="进行中" value="进行中" />
                  <el-option label="已完成" value="已完成" />
                </el-select>
              </el-form-item>
              <el-form-item label="是否超期未保">
                <el-radio-group v-model="reviseForm.mrexp">
                  <el-radio label="是" value="是"></el-radio>
                  <el-radio label="否" value="否"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="备注">
                <el-input
                  v-model="reviseForm.mrmark"
                  placeholder="不超过20个字"
                  type="textarea"
                ></el-input>
              </el-form-item>
            </el-form>
            <template #footer>
              <span class="dialog-footer">
                <el-button @click="reviseFormVisible = false">取消</el-button>
                <el-button type="primary" @click="handleRevise(ReviseData)">
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
        mrtime: "",
        mrentity: "",
        mrcontent: "",
        mrstate: "未开始",
        mrexp: "是",
        mrmark: "",
      },
      reviseFormVisible: false,
      ReviseData: {},
      reviseForm: {
        mrstate: "未开始",
        mrexp: "是",
        mrmark: "",
      },
      mrecord: [],
    });

    init();
    function init() {
      axios
        .get("mRecord", {
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
        state.addMRecord.mrtime == null ||
        state.addMRecord.mrtime == "" ||
        state.addMRecord.mrcontent == null ||
        state.addMRecord.mrcontent == "" ||
        state.addMRecord.mrentity == "" ||
        state.addMRecord.mrstate == "" ||
        state.addMRecord.mrexp == ""
      ) {
        ElMessage.warning("请完整填写维保记录信息！");
        return;
      }

      // 将设备ID和员工ID传到后端
      // 增加一条维保记录
      axios
        .post("mRecord", {
          did: state.addMRecord.did,
          eid: state.addMRecord.eid,
          mrtime: state.addMRecord.mrtime,
          mrcontent: state.addMRecord.mrcontent,
          mrentity: state.addMRecord.mrentity,
          mrstate: state.addMRecord.mrstate,
          mrexp: state.addMRecord.mrexp,
          mrmark: state.addMRecord.mrmark,
        })
        .then((response) => {
          if (response.data.includes("错误")) {
            ElMessage.error(response.data);
            return;
          } else {
            ElMessage.success(response.data);
          }
          // addMRecordVisible = false;
        })
        .catch((error) => {
          console.error(error);
        });
    };

    const getRowReviseData = (mrecord) => {
      state.ReviseData = mrecord;
      state.reviseFormVisible = true;
    };

    // 根据设备Id修改维保记录
    const handleRevise = (mrecord) => {
      if (
        state.reviseForm.mrstate == "" ||
        state.reviseForm.mrexp == "" ||
        state.reviseForm.mrmark == ""
      ) {
        ElMessage.warning("请输入修改类型！");
        return;
      }
      axios
        .put("mRecord/mrid", {
          mrid: mrecord.mrid,
          mrtime: mrecord.mrtime,
          mrcontent: mrecord.mrtime,
          mrentity: mrecord.mrentity,
          mrstate: state.reviseForm.mrstate,
          mrexp: state.reviseForm.mrexp,
          mrmark: state.reviseForm.mrmark,
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
      getRowReviseData,
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
