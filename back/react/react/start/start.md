---
slug: index
date: 2019-11-10
title: "React 시작"
description: "리액트(React)에 대해 설치 부터 실행하는 가이드"
toc: true
---

## 리액트 시작

### 리액트 설치 (yarn)
```
$ yarn global add create-react-app
```

### 리액트 프로젝트 생성
```
$ create-react-app react-project
```

### 리액트 서버 실행 / localhost:3000
```
cd react-project
yarn start
```

## 리엑트 설정

### 불필요 파일 제거
```
App.css
App.js
App.test.js
index.css
logo.svg
```

### nodemon
코드를 변경할 때 마다 서버를 자동으로 재시작 한다.
```
$ yarn add --dev nodemon
```

### NODE_PATH

Window 환경은 cross-env 설치 후 명령어 앞에 붙여줌.
```
$ yarn add --dev cross-env
```

방법1
```
.env

NODE_PATH=src
```

방법2
```
package.json - script

"scripts": {
    "start": "cross-env NODE_PATH=src react-scripts start",
    src/index.js"
}
```

이후 아래와 같이 import 및 경로 불러올 시에 src이하 경로로 인식

```
import App from 'components/App';
```

## 리액트 문서
https://ko.reactjs.org/docs/hooks-reference.html