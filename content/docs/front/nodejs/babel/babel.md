---
weight: 1
slug: index
date: 2022-05-17
title: "babel(바벨)"
description: "babel은 입력한 JavaSciprt(ES6~) 코드를 컴파일 하여 JavaScript(ES) 로 출력하는 컴파일러 입니다. JavaScript ES6 문법은 브라우저가 이해하지 못하는 문제가 있어 ES6 문법을 사용하는 개발환경에선 babel을 사용해 브라우저가 이해할 수 있는 문법으로 컴파일 합니다.Node.js를 사용하여 구축한 프론트 프레임워크 개발환경에서는 대체로 JavaScript는 ES6를 많이 사용하므로, 개발환경에 babel을 설치하여 같이 환경을 구축하는 개발방법이 주로 사용됩니다."
toc: true
---

## 이해하기

babel은 입력한 JavaSciprt(ES6~) 코드를 컴파일 하여 JavaScript(ES) 로 출력하는 컴파일러 입니다.

JavaScript ES6 문법은 브라우저가 이해하지 못하는 문제가 있어
<br>ES6 문법을 사용하는 개발환경에선 babel을 사용해 브라우저가 이해할 수 있는 문법으로 컴파일 합니다.

<br>

프론트 프레임워크 개발환경에서는 대체로 Node.js와 JavaScript ES6를 많이 사용하므로,
<br>개발환경에 babel을 설치하여 개발 환경을 구축하는 개발방법이 주로 사용됩니다.

## 시작하기

### 설치

```
$ yarn add @babel/preset-env --save
```