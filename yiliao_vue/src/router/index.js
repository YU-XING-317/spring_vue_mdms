import {createRouter, createWebHashHistory} from "vue-router";
import Main from "../views/Main.vue";
import DeviceManagement from "../components/DeviceManagement.vue";
import Maintenance from "../components/Maintenance.vue";
import Repair from "../components/Repair.vue";
import RiskControl from "../components/RiskControl.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: Main,
  },
  {
    path: "/main",
    name: "main",
    component: Main,
    children: [
      {
        path: "/deviceManagement",
        component: DeviceManagement,
      },
      {
        path: "/maintenance",
        component: Maintenance,
      },
      {
        path: "/repair",
        name: "repair",
        component: Repair,
      },
      {
        path: "/riskControl",
        component: RiskControl,
      },
    ],
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
