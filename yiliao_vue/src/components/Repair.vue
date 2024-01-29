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
          <el-button @click="addRRecordVisible = true">增加维修记录</el-button>
        </el-form-item>
      </el-form>
      <el-dialog v-model="addRRecordVisible" title="增加维修记录">
        <el-form :model="addRRecord" class="add-rrecord" size="small">
          <el-form-item label="维修设备注册编号">
            <el-input v-model="addRRecord.did"></el-input>
          </el-form-item>
          <el-form-item label="维修人员编号">
            <el-input v-model="addRRecord.eid"></el-input>
          </el-form-item>
          <el-form-item label="维修时间">
            <el-date-picker
              v-model="addRRecord.rrTime"
              palceholder="选择一个时间"
              range-separator="-"
              type="date"
              value-format="YYYY-MM-DD"
            ></el-date-picker>
          </el-form-item>
          <el-form-item label="维修内容">
            <el-input v-model="addRRecord.rrContent"></el-input>
          </el-form-item>
          <el-form-item label="维修单位">
            <el-input v-model="addRRecord.rrEntity"></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-select v-model="addRRecord.rrState">
              <el-option label="未检验" value="未检验" />
              <el-option label="已检验" value="已检验" />
            </el-select>
          </el-form-item>
          <el-form-item label="检验结果">
            <el-radio-group v-model="addRRecord.rrResult">
              <el-radio label="合格" value="合格"></el-radio>
              <el-radio label="不合格" value="不合格"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="备注">
            <el-input
              v-model="addRRecord.rrMark"
              placeholder="不超过20个字"
              type="textarea"
            ></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="addRRecordVisible = false">取消</el-button>
            <el-button type="primary" @click="addRRecordNoId"> 确认 </el-button>
          </span>
        </template>
      </el-dialog>
    </div>
    <el-table :data="rrecord" height="88%" stripe>
      <el-table-column type="expand">
        <template #default="props">
          <p style="margin: 10px; text-align: left">
            <b>备注: </b>&nbsp;
            {{ props.row.rrMark == "" ? "无" : props.row.rrMark }}
          </p>
        </template>
      </el-table-column>
      <el-table-column label="维修时间" prop="rrTime"></el-table-column>
      <el-table-column label="维修内容" prop="rrContent"></el-table-column>
      <el-table-column label="状态" prop="rrState"></el-table-column>
      <el-table-column label="维修结果" prop="rrResult"></el-table-column>
      <el-table-column label="维修人员" prop="employer.ename"></el-table-column>
      <el-table-column label="维修设备" prop="device.dname"></el-table-column>
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
                <el-select v-model="reviseForm.rrState">
                  <el-option label="未检验" value="未检验" />
                  <el-option label="已检验" value="已检验" />
                </el-select>
              </el-form-item>
              <el-form-item label="检验结果">
                <el-radio-group v-model="reviseForm.rrResult">
                  <el-radio label="合格" value="合格"></el-radio>
                  <el-radio label="不合格" value="不合格"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="备注">
                <el-input
                  v-model="reviseForm.rrMark"
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
      addRRecordVisible: false,
      addRRecord: {
        did: "",
        eid: "",
        rrTime: "",
        rrContent: "",
        rrState: "未检验",
        rrResult: "合格",
        rrMark: "",
      },
      reviseFormVisible: false,
      reviseForm: {
        rrState: "未检验",
        rrResult: "合格",
        rrMark: "",
      },
      rrecord: [
        {
          rrId: "123",
          rrTime: "2023-12-01",
          rrContent: "aabcddfas",
          rrState: "未检验",
          rrResult: "合格",
          rrMark: "",
          device: { dname: "hello" },
          employer: { ename: "gegegegege" },
        },
      ],
    });
    function init() {
      axios
        .post("repairRecord/getRepairRecordByDName", {
          dname: state.formQuery.dname,
        })
        .then((response) => {
          state.rrecord = response.data;
          console.log(state.rrecord); // 控制台输出结果
        })
        .catch((error) => {
          console.error(error);
        });
    }
    init();

    // 增加维保记录，无did
    function addRRecordNoId() {
      if (
        state.addRRecord.did == "" ||
        state.addRRecord.eid == "" ||
        state.addRRecord.rrTime == "" ||
        state.addRRecord.rrContent == "" ||
        state.addRRecord.rrState == "" ||
        state.addRRecord.rrResult == ""
      ) {
        ElMessage.warning("请完整填写维检验记录信息！");
        return;
      }

      // 将设备ID和员工ID传到后端
      // 增加一条维保记录
      axios
        .post("repairRecord/insertRepairRecord", {
          did: state.addRRecord.did,
          eid: state.addRRecord.eid,
          rrTime: state.addRRecord.rrTime,
          rrContent: state.addRRecord.rrContent,
          rrState: state.addRRecord.rrState,
          rrResult: state.addRRecord.rrResult,
          rrMark: state.addRRecord.rrMark,
        })
        .then((response) => {
          if (response.data.includes("错误")) {
            ElMessage.error(response.data);
            return;
          } else {
            ElMessage.success(response.data);
          }
          addRRecordVisible = false;
        })
        .catch((error) => {
          console.error(error);
        });
    }
    // 根据设备Id修改维保记录
    function handleRevise(rrecord) {
      if (state.reviseForm.rrState == "" || state.reviseForm.rrResult == "") {
        ElMessage.warning("请输入完整信息！");
        return;
      }
      axios
        .post("repairRecord/alterRepairRecord", {
          rrId: rrecord.rrId,
          rrTime: rrecord.rrTime,
          rrContent: rrecord.rrContent,
          rrState: state.reviseForm.rrState,
          rrResult: state.reviseForm.rrResult,
          rrMark: state.reviseForm.rrMark,
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
    }

    function tableSearch() {
      init();
    }

    function resetSearch() {
      state.formQuery.dname = "";
    }
    return {
      ...toRefs(state),
      handleRevise,
      tableSearch,
      resetSearch,
      addRRecordNoId,
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
