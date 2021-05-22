---
weight: 1
slug: index
date: 2021-05-06
title: "NodeJS 환경설정"
description: "NodeJS 환경설정 가이드"
toc: true
---

## process.env

Nodejs는 환경변수에 따라 개발, 운영 환경을 구분해서 관리가 가능합니다.

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

루트 경로에 .env.development 파일을 추가하고 아래와 같이 작성합니다.
```
// .env.development

API=http://test.com
```

작성 후, process.env의 속성 값으로 


```
console.log(process.env.NODE_ENV)
console.log(process.env.API)

// development
// http://test.com
```
