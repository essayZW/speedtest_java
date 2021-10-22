module.exports = {
  assetsDir: 'static',
  pages: {
    index: {
      entry: 'src/pages/index/main.js',
      template: 'public/index.html',
      filename: 'index.html',
      title: 'Speedtest-测速网站'
    },
    userLogin: {
      entry: 'src/pages/userLogin/main.js',
      template: 'public/index.html',
      filename: 'login.html',
      title: '登录'
    }
  },
  devServer: {
    port: 8000
  },
  chainWebpack: (config) => {
    config.module
      .rule("worker")
      .test(/\.worker\.js$/)
      .use("worker-loader")
      .loader("worker-loader")
      .options({
        inline: "fallback"
      });
    config.module.rule("js").exclude.add(/\.worker\.js$/);
  },
}
