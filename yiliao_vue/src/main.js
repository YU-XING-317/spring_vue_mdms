import {createApp} from "vue";
import App from "./App.vue";
import router from "./router";
import "font-awesome/css/font-awesome.min.css";
// 导入ElementUi
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";

createApp(App).use(router).use(ElementPlus).mount("#app");

//import {createApp} from 'vue'
//import App from './App.vue'
//
//createApp(App).mount('#app')
