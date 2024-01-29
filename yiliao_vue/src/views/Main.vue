<template>
  <div class="wrapper">
    <el-header class="header">
      <div class="header-left">
        <img alt="logo.png" src="@/assets/logo.png" />
        <p>医疗设备大数据预警平台</p>
      </div>
      <div class="header-right">
        <div>
          <p>退出系统</p>
        </div>
        <div>
          <p>修改密码</p>
        </div>
      </div>
    </el-header>
    <el-container style="display: flex; justify-content: space-between">
      <el-aside class="aside">
        <el-row class="aside-nav">
          <el-col>
            <h3 class="nav-title">功能菜单</h3>
            <el-menu
              :default-openeds="[menus[0].id]"
              active-text-color="blue"
              background-color="#a5d6e0"
              router
              text-color="#696969"
            >
              <el-sub-menu
                v-for="menu in menus"
                :key="menu.id"
                :index="menu.id"
              >
                <template #title>
                  <p style="font-weight: bold; font-size: 1.3vw">
                    {{ menu.title }}
                  </p>
                </template>
                <el-menu-item
                  v-for="item in menu.items"
                  :key="item.id"
                  :index="'/' + item.path"
                >
                  <p style="font-size: 1.2vw">{{ item.authName }}</p>
                </el-menu-item>
              </el-sub-menu>
            </el-menu>
          </el-col>
        </el-row>
      </el-aside>
      <!-- 路由跳转显示 -->
      <el-main class="main"><router-view></router-view></el-main>
    </el-container>
  </div>
</template>

<script>
import {reactive, toRefs} from "vue";

export default {
  setup() {
    const state = reactive({
      menus: [
        {
          id: "1",
          title: "医疗设备运营",
          items: [
            { id: "1-1", path: "Maintenance", authName: "维保事件管理" },
            { id: "1-2", path: "Repair", authName: "检验问题管理" },
            { id: "1-3", path: "RiskControl", authName: "设备风险预警" },
          ],
        },
        {
          id: "2",
          title: "基本信息",
          items: [
            { id: "2-1", path: "DeviceManagement", authName: "医疗设备" },
          ],
        },
      ],
    });
    return {
      ...toRefs(state),
    };
  },
};
</script>

<style scoped>
/* 总容器 */
.wrapper {
  height: 100%;
  width: 100%;
  background-color: #f9f9f9;
}
.header {
  width: 100%;
  height: 10vh;
  background-color: rgb(24, 37, 94);

  position: fixed;
  display: flex;
  align-items: center;
  justify-content: space-between;

  box-sizing: border-box;
  padding: 0 3.6vw;
}

.header .header-left {
  display: flex;
  align-items: center;
}

.header .header-left img {
  width: 18%;
  margin-right: 15px;
}

.header .header-left p {
  color: rgb(90, 134, 236);
  font-size: 2vw;
  align-items: center;
  line-height: 2vw;
}

.header .header-right {
  color: #ffffff;
  display: flex;
  align-items: center;
}

.header .header-right div {
  height: 20px;
  margin: 10px;
  padding: auto;
  line-height: 20px;
  display: flex;
  align-items: center;
}

.aside {
  position: fixed;
  top: 10vh;
  width: 15vw;
  height: 90vh;
  border-right: 3px rgb(23, 40, 92) solid;
  background-color: rgb(90, 133, 235);
}

.aside .aside-nav {
  width: 100%;
  height: 100%;
}

.aside .aside-nav .nav-title {
  background-color: rgb(90, 133, 235);
  color: #ffffff;

  text-align: left;
}

.main {
  margin-top: 10vh;
  margin-left: 15vw;
  background-color: #f9f9f9;
}
</style>
