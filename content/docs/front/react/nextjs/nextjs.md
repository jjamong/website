---
weight: 1
slug: index
date: 2021-05-06
title: "Next.js"
description: "리액트 Next.js 가이드"
toc: true
---

## 시작하기

`https://nextjs.org/`

Next.js는 라우트에 해당된 파일들을 소문자로 pages 폴더에 추가해야 하는 정형화된 구조를 따르게 됩니다. 


### 프로젝트 설정 및 next 설치

프로젝트를 위한 폴더를 추가하고, react와 next를 설치합니다.
```
$ yarn init -y
$ yarn add react react-dom next
```

package.json 파일에 아래 내용을 추가합니다.
```
// package.json

...
"scripts": {
    "dev": "next"
},
...

```

루트 경로에 pages 폴더를 추가하고 폴더 경로에 index.js파일을 추가합니다.
```
// pages/index.js

const Index = () => (
    <div>
        Next.js
    </div>
);

export default Index;
```

### 실행

아래 명령어를 통해 실행합니다.
```
$ yarn run dev
```

![Next.js시작하기](/docs/front/react/nextjs/01.png)