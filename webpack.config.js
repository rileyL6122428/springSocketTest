var ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = {
    entry: ["./frontend/scripts/bootstrap.ts", "./frontend/styles/trivia.scss"],
    output: {
        filename: "src/main/webapp/bundle.js"
    },

    // rules: [
    //   {
    //     test: /\.css$/,
    //     loader: ExtractTextPlugin.extract({
    //       loader: 'css-loader?importLoaders=1',
    //     })
    //   },
    //
    //
    // ],
    stats: {
      warnings: false
    },

    resolve: {
        alias: {
          stompjs: __dirname + '/node_modules/stompjs/lib/stomp.js',
          // "ng2-stompjs": __dirname + '/node_modules/stompjs/lib/stomp.js'
        },
        extensions: [".webpack.js", ".web.js", ".js", ".ts", ".tsx"]
    },
    module: {
        loaders: [
            // all files with a '.ts' or '.tsx' extension will be handled by 'ts-loader'
            { test: /\.tsx?$/, loader: "ts-loader" },

            { // sass / scss loader for webpack
              test: /\.(sass|scss)$/,
              loader: ExtractTextPlugin.extract(['css-loader', 'sass-loader'])
            }
        ]
    },

    plugins: [
      new ExtractTextPlugin({ // define where to save the file
        filename: 'src/main/webapp/bundle.css',
        allChunks: true,
      })
    ]
}
