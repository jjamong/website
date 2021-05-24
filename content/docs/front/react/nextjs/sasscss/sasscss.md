---
weight: 1
slug: index
date: 2021-05-23
title: "Sass & CSS"
description: "리액트 Next.js > sass & css 가이드"
toc: true
---

## 시작하기

sass, css를 사용하기 위해서는 몇가지를 설치할 필요가 있습니다.

`next v9.2` 이하의 버전일 경우 아래의 방식으로 sass, css를 사용 할 수 있습니다.
```
// package.json

{
  "name": "sasscss",
  "version": "1.0.0",
  "main": "index.js",
  "license": "MIT",
  "scripts": {
    "dev": "next"
  },
  "dependencies": {
    "@zeit/next-sass": "1.0.1",
    "@zeit/next-css": "1.0.1",
    "next": "^9.0.0",
    "node-sass": "^4.0.0",
    "react": "17.0.2",
    "react-dom": "17.0.2"
  }
}

```

`@zeit/next-sass`, `@zeit/next-css`, `node-sass`을 설치합니다.
```
$ yarn install
```


```
// next.config.js

const withSass = require('@zeit/next-sass');
const withCSS = require('@zeit/next-css');

module.exports = withCSS(withSass());
```

```
// style.css

body {color: red;}
```


```
// style.scss

$color: #666;

body {
    background-color: $color;
}
```


```
// pages/index.js

import '../style.scss';
import '../style.css';

const Index = () => (
    <div>
        Next.js
    </div>
);

export default Index;
```

위와 같이 파일을 작성하고 실행하면 아래와 같이 sass와 css가 모두 적용 된 것을 확인 할 수 있습니다.

![01](/docs/front/react/nextjs/sasscss/01.png)