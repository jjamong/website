---
title: 프로젝트 설정
date: 2020-01-07
description: 프로젝트를 생성하고 react 플러그인을 설치합니다.
toc: true
slug: index
---

자, 이제 프로젝트를 생성하고, 플러그인들을 설치하여 기본 스텐바이를 합니다.
그리고 index.html, sass 설정 등 프로젝트 소스를 작성하기 전,
필요한 기본 설정 등도 작성하도록 하겠습니다.

## D2 프로젝트 생성 및 플러그인 설정

D2 프로젝트 생성 후 localhost:3000에 리액트 프로젝트 확인

[참고](/docs/front/react/)

```
$ create-react-app d2

$ cd d2
$ yarn start
```

- - -

yarn 초기화

[참고](/docs/etc/yarn/)

```
$ yarn eject
```

- - -

NODE_PATH 설정

[참고](/docs/front/react/)

```
$ yarn add --dev cross-env
```
```
.env 파일 아래 내용 추가

NODE_PATH=src
```
- - -

router 추가

[참고](/docs/front/react/setting/router/)

```
$ yarn add react-router-dom
```

- - -

sass 설정

[참고](/docs/front/react/setting/sass/)

```
$ yarn add node-sass classnames
```

설정 후 globalStyles 설정

## 프로젝트 구조 설정

### index.html 설정

`./src/index.html`

```
import React from 'react';
import ReactDOM from 'react-dom';
import Root from './Root';
import * as serviceWorker from './serviceWorker';
import './assets/styles/sass/styles.scss'

ReactDOM.render(<Root />, document.getElementById('root'));


// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
```

index.html의 Root컴포넌트를 설정합니다. (Root.js 추가)<br/>

추가로, 아래 style.scss는 sass파일의 root파일을 설정합니다.(styles.scss)
```
import './assets/styles/sass/styles.scss'
```

