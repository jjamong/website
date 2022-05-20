---
weight: 1
slug: index
date: 2021-05-06
title: "Node.js(노드JS)"
description: "NodeJS 환경설정(.env) 가이드"
toc: true
---

## 이해하기

Node.js는 Chrome(크롬) V8 JavaScript(자바스크립트) 엔진으로 빌드 된 JavaScript 런타임 환경입니다.

Node.js를 통해서 다양한 JavaScript 애플리케이션을 실행할 수 있고, 서버를 실행하는데 대표적으로 사용됩니다.

좀 더 쉽게 설명하자면 JavaScript는 웹 브라우저 환경에서 동작하는 스크립트 언어인데
<br>
Node.js를 설치하여 웹 브라우저가 아닌 터미널, 서버 등 다른 환경에서도 실행 할 수 있도록 하는 환경이라고 할 수 있습니다.

<br>

**Node.js는 JavaScript를 실행할 수 있도록 하는 환경이며
<br>프론트 엔드 프레임워크(React, Angular, Vue)를 사용하는데 기반이 되는 환경입니다.**

**Node.js는 I/O와 단일 스레드 처리에 대한 높은 성능이 특징이어서
<br>실시간 채팅, 실시간 예매 등 비동기식 처리 서버를 구현 하는데도 좋습니다.**

### JavaScript RunTime(자바스크립트 런타임)

런타임이란 용어는 특정 언어로 만든 프로그램을 실행할 수 있는 환경을 뜻합니다.
<br>
Node.js에 대입해 보면 JavaScript로 작성한 코드를 실행할 수 있는 환경(터미널, 서버 등)이 됩니다.


<!-- 
https://velog.io/@kimkevin90/Nodejs-%EA%B0%9C%EB%85%90-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0Nodejs-%EB%9E%80
-->

<!--
논 블로킹I/0
이벤트 루프를 잘 활용하면 오래 걸리는 작업을 효율적으로 처리할 수 있습니다. 작업에는 두가지 종류가 있는데, 동시에 실행될 수 있는 작업과 동시에 실행될 수 없는 작업입니다. 특히 파일 시스템 접근, 네트워크를 통한 요청 작업은 I/O의 일종이며 이러한 작업을 할 때 노드는 논 브로킹 방식으로 처리합니다. 논 블로킹이란 이전 작업이 완료될 때까지 대기하지 않고 다음 작업을 수행하는 것을 의미합니다. 반대로 블로킹은 이전 작업이 끝나야만 다음 작업을 수행합니다.
-->

<!-- 
싱글 스레드
이벤트 기반, 논 블로킹 모델과 더불어 노드를 설명하는 키워드 중 하나는 싱글 스레드 입니다. 자바스크립트 코드는 동시에 실행될 수 없는데 그 이유는 노드가 싱글 스레드 기반이기 때문입니다.
-->

<!-- 
이벤트 루프
이벤트 루프(event loop)는 여러 이벤트가 동시에 발생했을 때 어떤 순서로 콜백 함수를 호출할지를 이벤트 루프가 판단합니다. 노드는 이벤트가 종료될 때까지 이벤트 처리를 위한 작업을 반복 하므로 루프(loop)라고 부릅니다.
-->

<!-- 
이벤트 기반
노드는 V8과 더불어 libuv라는 라이브러리르 사용합니다. libuv 라이브러리는 노드의 특성인 이벤트 기반, 논 블로킹 I/O 모델을 구현하고 있습니다.
이벤트 기반(event-driven)이란 이벤트가 발생할 때 미리 지정해둔 작업을 수행하는 방식을 의미합니다. 즉, 이벤트 기반 시스템에서는 특정 이벤트가 발생할 때 무엇을 할지 미리 등록해두고, 이를 이벤트 리스너에 콜백 함수를 등록 합니다. 이후 이벤트가 발생하면 리스너에 등록해둔 콜백 함수를 호출하며 이벤트가 끝난 후 노드는 다음 이벤트가 발생할 때까지 대기합니다.
-->



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