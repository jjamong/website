---
weight: 1
slug: index
date: 2021-05-06
title: "설정(.env)"
description: "NodeJS 환경설정(.env) 가이드"
toc: true
---

## 시작하기


### dotenv 패키지 설치

루트 경로에 package.json 을 추가합니다.
```
$ yarn init -y

// package.json
{
  "name": "nodejs",
  "version": "1.0.0",
  "main": "index.js",
  "license": "MIT",
  "dependencies": {
    "dotenv": "^8.2.0"
  }
}
```

dotenv 설치
```
$ yarn install
```

### 노드(server.js), 설정(.env) 파일 추가


루트경로 노드로 실행할 파일(server.js) 과 설정파일(.env)을 추가합니다.

```
// /server.js

const dotenv = require('dotenv')

// 환결설정 설정하기
dotenv.config()

console.log(process.env.PORT)
```

```
// .env

PORT=9999
```

server.js를 노드로 실행합니다.
```
$ node server.js

// 9999
```


### 파라미터

node 실행 시 파라미터를 추가할 수 있습니다.

server.js 파일을 수정합니다.

```
// /server.js

const dotenv = require('dotenv')

// 환결설정 설정하기
dotenv.config()

console.log(process.env.NODE_ENV)
console.log(process.env.PORT)
```

server.js를 파마리터와 함께 노드로 실행합니다.
```
// windows
$ set NODE_ENV=development && node server.js
// linux
$ NODE_ENV=development node server.js

// development
// 9999
```