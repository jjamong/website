---
title: SASS loader 설정
date: 2019-11-11
slug: index
description: react에 SASS를 설정하는 방법을 설명한다. loader 설치 부터, webpack 설정 및 기타 플러그인 설치 등 react에서 사용되는 sass활용법 정보다.
---

react에 SASS를 설정하는 방법을 설명한다.
loader 설치 부터, webpack 설정 및 기타 플러그인 설치 등
react에서 사용되는 sass활용법 정보다.


## SASS 설정

## sass-loader

node-sass 는 sass 로 작성된 코드들을 CSS로 변환한다.

```
$ yarn add node-sass
```

## classnames 설정

클래스를 여러개 적용 할 수 있다.

```
$ yarn add classnames
```

## globalStyles 설정

sass 경로를 간소화 할 수 있다.

`includePaths` 경로는 sass Root경로를 설정한다.

```
webpack.config.js - 아래쪽

{
  test: sassRegex,
  exclude: sassModuleRegex,
  use: getStyleLoaders({
    importLoaders: 2,
    sourceMap: isEnvProduction && shouldUseSourceMap
  }).concat({
    loader: require.resolve('sass-loader'),
    options: {
      sassOptions: {
        includePaths: [paths.appSrc + '/styles'],
        sourceMap: isEnvProduction && shouldUseSourceMap
      }
    }
  }),
  sideEffects: true
},

```

설정 후 아래와 같이 사용 가능하다.

```
@import "utils.scss";
```

## 플러그인

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

## 참고

[Sass 폴더 구조](http://www.webactually.co.kr/archives/13106)