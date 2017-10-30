module.exports = function (config) {
    config.set({
        basePath: '',
        frameworks: ['jasmine'],
        files: [
          // 'frontend/specs/**/*.spec.ts'
          { pattern: "frontend/specs/**/*.spec.ts" }
        ],
        mime: { 'text/x-typescript': ['ts','tsx'] },
        preprocessors: {
            'spec/main.spec.ts': ['webpack', 'sourcemap'],
        },
        webpack: {
            resolve: {
                extensions: ['.js', '.ts', '.tsx']
            },
            module: {
                loaders: [
                    {test: /\.ts?$/, loader: 'ts-loader'}
                ]
            },
            stats: {
                colors: true,
                modules: true,
                reasons: true,
                errorDetails: true
            },
            devtool: 'inline-source-map',
        },
        reporters: ['progress'],
        port: 9876,
        colors: true,
        logLevel: config.LOG_INFO,
        autoWatch: true,
        browsers: ['Chrome'],
        singleRun: false,
        concurrency: Infinity
    })
}
