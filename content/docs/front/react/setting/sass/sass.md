---
title: Sass 적용
date: 2019-11-11
weight: 1
slug: index
---

## CSS Module 설정

CSS Module은, CSS를 모듈화하여 사용하는 방식이다.
CSS를 웹팩을 통해서 불러오면, 고유화된 클래스네임이 이뤄진 객체가 반환되며, 
```
{
  box: 'src-App__box--mjrNr'
}
```
그리고 클래스를 적용하려면 `className={style.box}` 이와 같이 사용한다.


webpack.config.js 파일에서 아래와 같이 수정한다.
webpack.config.js 는 나중에 리액트를 빌드하는 과정에서 사용되는 환경설정 파일입니다.

```
(...)
    {
        // Options for PostCSS as we reference these options twice
        // Adds vendor prefixing based on your specified browser support in
        // package.json
        loader: require.resolve('postcss-loader'),
        options: {
          // Necessary for external CSS imports to work
          // https://github.com/facebook/create-react-app/issues/2677
          ident: 'postcss',
          plugins: () => [
            require('postcss-flexbugs-fixes'),
            require('postcss-preset-env')({
              autoprefixer: {
                flexbox: 'no-2009',
                browsers: [
                  '>1%',
                  'last 4 versions',
                  'Firefox ESR',
                  'not ie < 9', // React doesn't support IE8 anyway
                ]
              },
              stage: 3,
            }),
            // Adds PostCSS Normalize as the reset css with default options,
            // so that it honors browserslist config in package.json
            // which in turn let's users customize the target behavior as per their needs.
            postcssNormalize(),
          ],
          sourceMap: isEnvProduction && shouldUseSourceMap,
        },
      },
(...)
```

```
(...)

    // common function to get style loaders
    const getStyleLoaders = (cssOptions, preProcessor) => {
    const loaders = [
        isEnvDevelopment && require.resolve('style-loader'),
        isEnvProduction && {
        loader: MiniCssExtractPlugin.loader,
        options: shouldUseRelativeAssetPaths ? { publicPath: '../../' } : {},
        },
        {
        loader: require.resolve('css-loader'),
        //options: cssOptions,
        options:{
            modules: true,
            localIdentName: '[path][name]__[local]--[hash:base64:5]'
        },
    },

(...)
```

위의 설정을 통하면, css가 적용된 것을 확인 할 수 있다.

## classnames 설정
클래스를 여러개 적용 할 수 있다.
```
$ yarn add classnames
```

## SASS 설정

sass-loader 는 webpack 에서 sass 파일들을 읽어오는 역할을 하고, node-sass 는 sass 로 작성된 코드들을 CSS로 변환한다.

```
$ yarn add node-sass sass-loader
```


### open-color 설정

변수 세트 라이브러리를 사용하며,
색상을 간편하게 불러올 수 있음.

```
$ yarn add open-color
```

```
@import '~open-color/open-color';
```

### include-media 설정

믹스인 라이브러리 반응형 디자인 지원

```
$ yarn add include-media
```


## globalStyles 설정
sass 경로를 간소화 할 수 있다.

```
config.paths.js - 아래쪽

(...)
module.exports = {
  (...)
  globalStyles: resolveApp('src/styles')
}
(...)
```
