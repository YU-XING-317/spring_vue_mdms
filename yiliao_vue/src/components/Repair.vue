<template>
  <div class="wrapper">
    <div class="table-control">
      <el-form :inline="true" :model="formQuery" class="form-query">
        <el-form-item label="设备ID">
          <el-input v-model="formQuery.did"></el-input>
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
<!--            <el-input v-model="addRRecord.did"></el-input>-->
            <el-select v-model="addRRecord.did" placeholder="请选择" @focus="getids">
              <el-option
                  v-for="item in ids"
                  :key="item.did"
                  :label="item.did"
                  :value="item.did"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="维修人员编号">
            <el-input v-model="addRRecord.eid"></el-input>
          </el-form-item>
          <el-form-item label="维修时间">
            <el-date-picker
              v-model="addRRecord.rrtime"
              palceholder="选择一个时间"
              range-separator="-"
              type="date"
              value-format="YYYY-MM-DD"
            ></el-date-picker>
          </el-form-item>
          <el-form-item label="维修内容">
            <el-input v-model="addRRecord.rrcontent"></el-input>
          </el-form-item>
          <el-form-item label="维修单位">
            <el-input v-model="addRRecord.rrEntity"></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-select v-model="addRRecord.rrstate">
              <el-option label="未检验" value="0" />
              <el-option label="已检验" value="1" />
            </el-select>
          </el-form-item>
          <el-form-item label="检验结果">
            <el-radio-group v-model="addRRecord.rrresult">
              <el-radio label="1" value="1">合格</el-radio>
              <el-radio label="2" value="2">不合格</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="备注">
            <el-input
              v-model="addRRecord.rrmark"
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
            {{ props.row.rrmark == "" ? "无" : props.row.rrmark }}
          </p>
        </template>
      </el-table-column>
      <el-table-column label="维修记录编号" prop="rrid"></el-table-column>
      <el-table-column label="维修时间" prop="rrtime"></el-table-column>
      <el-table-column label="维修内容" prop="rrcontent"></el-table-column>
      <el-table-column label="状态" prop="rrstate">
        <template v-slot="scope">
          <p v-if="scope.row.rrstate=='0'">未检验</p>
          <p v-else-if="scope.row.rrstate=='1'">已检验</p>
        </template>
      </el-table-column>
      <el-table-column label="维修结果" prop="rrresult">
        <template v-slot="scope">
          <p v-if="scope.row.rrresult=='1'">合格</p>
          <p v-else-if="scope.row.rrresult=='2'">不合格</p>
        </template>
      </el-table-column>
      <el-table-column label="维修人员id" prop="eid"></el-table-column>
      <el-table-column label="维修设备id" prop="did"></el-table-column>
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
                <el-select v-model="reviseForm.rrstate">
                  <el-option label="未检验" value="0" />
                  <el-option label="已检验" value="1" />
                </el-select>
              </el-form-item>
              <el-form-item label="检验结果">
                <el-radio-group v-model="reviseForm.rrresult">
                  <el-radio label="1" value="1">合格</el-radio>
                  <el-radio label="2" value="2">不合格</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="备注">
                <el-input
                  v-model="reviseForm.rrmark"
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
      ids:[],
      formQuery: {
        did: "",
      },
      addRRecordVisible: false,
      addRRecord: {
        did: "",
        eid: "",
        rrtime: "",
        rrcontent: "",
        rrstate: "0",
        rrresult: "1",
        rrmark: "",
      },
      reviseFormVisible: false,
      ReviseData: {},
      reviseForm: {
        rrstate: "0",
        rrresult: "1",
        rrmark: "",
      },
      rrecord: [
        {
          rrid: "123",
          rrtime: "2023-12-01",
          rrcontent: "aabcddfas",
          rrstate: "未检验",
          rrresult: "合格",
          rrmark: "",
          device: { dname: "hello" },
          employer: { ename: "gegegegege" },
        },
      ],
    });
    function init() {
      axios({
        url: "rRecord",
        params:{
          did: state.formQuery.did,
        },
        method:"get"
      }).then((response) => {
        state.rrecord = response.data;
        console.log(state.rrecord); // 控制台输出结果
      }).catch((error) => {
        console.error(error);
      });
    }
    init();

    // 增加维保记录，无did
    function addRRecordNoId() {
      if (
        state.addRRecord.did == "" ||
        state.addRRecord.eid == "" ||
        state.addRRecord.rrtime == "" ||
        state.addRRecord.rrcontent == "" ||
        state.addRRecord.rrstate == "" ||
        state.addRRecord.rrresult == ""
      ) {
        ElMessage.warning("请完整填写维检验记录信息！");
        return;
      }

      // 将设备ID和员工ID传到后端
      // 增加一条维保记录
      axios
        .post("rRecord", {
          did: state.addRRecord.did,
          eid: state.addRRecord.eid,
          rrtime: state.addRRecord.rrtime,
          rrcontent: state.addRRecord.rrcontent,
          rrstate: state.addRRecord.rrstate,
          rrresult: state.addRRecord.rrresult,
          rrmark: state.addRRecord.rrmark,
        })
        .then((response) => {
          if (response.data.includes("错误")) {
            ElMessage.error(response.data);
            return;
          } else {
            ElMessage.success(response.data);
            init();
          }
          //addRRecordVisible = false;
        })
        .catch((error) => {
          console.error(error);
        });
      // 清空表单
      state.addRRecord = {
        did: "",
        eid: "",
        rrtime: "",
        rrcontent: "",
        rrstate: "0",
        rrresult: "1",
        rrmark: "",
      };
      // 刷新页面
      init();
    }

    //获取所有设备id
    function getids(){
      axios({
        url: "/device/id",
        method: "get",
      }).then((response) => {
        state.ids = response.data;
        console.log(response);
      }).catch((error) => {
        console.error(error);
      });
    }

    const getRowReviseData = (rrecord) => {
      state.ReviseData = rrecord;
      state.reviseFormVisible = true;
    };

    // 根据设备Id修改维保记录
    function handleRevise(rrecord) {
      if (state.reviseForm.rrstate == "" || state.reviseForm.rrresult == "") {
        ElMessage.warning("请输入完整信息！");
        return;
      }
      console.log(rrecord);
      axios
        .put("repairRecord", {
          rrid: rrecord.rrid,
          rrtime: rrecord.rrtime,
          rrcontent: rrecord.rrcontent,
          rrstate: state.reviseForm.rrstate,
          rrresult: state.reviseForm.rrresult,
          rrmark: state.reviseForm.rrmark,
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
      state.formQuery.did = "";
      init()
    }
    return {
      ...toRefs(state),
      getRowReviseData,
      handleRevise,
      tableSearch,
      resetSearch,
      addRRecordNoId,
      getids,
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
