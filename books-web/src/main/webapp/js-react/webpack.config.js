/**
 * Created by 杨惠宇 of666 on 2015/5/21 0021.
 */
'use strict'
module.exports = {
    entry: {
       'app':'./public/views/app.js',
       'route':'./public/views/router.js'
    },                 //打包的js
    output: {                                            //输出信息
    path: './public/build/',
    filename: 'qianmi-[name].js'
},
module: {                                         //处理jsx的编译
    loaders: [
        {test: /\.js$/, loader: 'babel-loader'}
    ]
}
};