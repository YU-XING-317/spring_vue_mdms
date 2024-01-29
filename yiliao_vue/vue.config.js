const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 8081, // 修改启动端口
  },
  lintOnSave: false,
  publicPath: process.env.NODE_ENV === "production" ? "/yiliao/" : "/",
});

//const { defineConfig } = require('@vue/cli-service')
//module.exports = defineConfig({
//  transpileDependencies: true
//})
