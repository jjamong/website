---
weight: 1
slug: index
date: 2021-05-06
title: "시작하기"
description: "NodeJS 시작하기"
toc: true
---

## process

Node 프로세스 정보를 가져옵니다.

```
$ process

process {
  version: 'v12.13.0',
  versions: {
    node: '12.13.0',
    v8: '7.7.299.13-node.12',
    uv: '1.32.0',
    zlib: '1.2.11',
    b',
    TMP: 'C:\\Users\\gudrb\\AppData\\Local\\Temp',
    USERDOMAIN: 'DESKTOP-S7UF6GB',
    USERDOMAIN_ROAMINGPROFILE: 'DESKTOP-S7UF6GB',
    USERNAME: 'gudrb',
    USERPROFILE: 'C:\\Users\\gudrb',
    windir: 'C:\\WINDOWS'
  },
  title: 'Node.js',
  argv: [ 'D:\\program\\nodejs\\node.exe' ],
  ...
}
```

## Node 실행

루트경로 노드로 실행할 파일(server.js) 과 설정파일(.env)을 추가합니다.


```
// ./server.js

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
// ./server.js

console.log(process.env.NODE_ENV)
console.log(process.env.PORT)
```

server.js를 파마리터와 함께 노드로 실행합니다.
```
$ NODE_ENV=development node server.js

// development
// 9999
```

### package.json

package.json 을 추가합니다.
```
$ yarn init -y
```

```
// package.json

"scripts": {
  //windows
  "start": "set NODE_ENV=development && node server.js",
  //linux
  "start": "NODE_ENV=development node server.js"
}
```

server.js를 실행합니다.
```
yarn start

// development
// 9999
```
