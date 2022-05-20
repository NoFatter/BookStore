module.exports = {
    publicPath: '/',
    outputDir: 'dist', // 打包的目录
    lintOnSave: true, // 在保存时校验格式
    productionSourceMap: false, // 生产环境是否生成 SourceMap
    devServer: {
        open: true, // 启动服务后是否打开浏览器
        host: 'localhost',
        port: 8080, // 服务端口
        https: false,
        hotOnly: false,
        proxy: {
            '/api': { 
                //名字必须跟application context相同 否则404
                target: 'http://localhost:8088/springboot',
                changeOrigin: true, //  跨域
                pathRewrite: {
                    '^/api': ''
                }
            }
        }, // 设置代理
        before: app => {}
    },
}