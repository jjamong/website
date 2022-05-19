---
weight: 1
slug: index
date: 2022-05-19
title: "ES(ECMAScript, 에크마스크립트)"
description: ""
toc: true
---

## 이해하기

ES(ECMAScript)는 2009년 표준화 된 ES5 이후로 2015년 ES6로 업데이트 되어 현재 JavaScript에서 
ES6를 많이 사용 중에 있습니다.

ES5는 jquery와 함께 사용되었던 var, function등을 사용한 javascript 버전이라고 할 수 있고,
<br>ES6는 현재 많이 사용되는 Node, React 등에 사용되는 버전이라고 할 수 있습니다.

<br>

[https://es6console.com/](https://es6console.com/) ES6를 ES5로 변환시켜주는 사이트 

## 문법

### const / let

- -const : 변경이 불가능한 변수로 상수로 사용됩니다.
- -let : 새로운 값도 선언될 수 있고, 재할당도 가능합니다.(var의 hoisting현상을 피하기 위함)

```
// es6

let test = 'test';
const test2 = 'test2';
```

```
// es5

var test = 'test';
var test2 = 'test2';
```


### Arrow functions(화살표 함수)
### Template Literals(템플릿 리터럴)
### Default parameters(기본 매개 변수)
### Array and object destructing(배열 및 객체 비구조화)
### Import and export(가져오기 및 내보내기)
### Promises(프로미스)
### Rest parameter and Spread operator(나머지 매개 변수 및 확산 연산자)
### Classes(클래스)