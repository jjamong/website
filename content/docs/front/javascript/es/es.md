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

### const, let

ES5까지 변수는 var를 사용했었습니다. var는 편리했지만 단점들이 크게 존재합니다.
<br>var의 단점으로는

함수 레벨 스코프(Function-level scope)로 함수의 코드 블록만 스코프로 인정합니다.
전역 함수 외에 생성한 모든 변수는 전역으로 선언됩니다.
<br>이 문제는 전역 변수를 남발할 가능성이 높습니다. 그리고 전역변수가 많이 생기는 만큼 변수 중복 선언이 될 가능성이 높아집니다.

for 문의 변수 선언문에서 선언한 변수를 for 문의 코드 블록 외부에서 참조할 수 있습니다.
<br>블록 단위로 스코프가 인정되지 않아 발생하는 케이스입니다.

변수 호이스팅 문제가 있습니다.
<br> 호이스팅이란 var 로 선언한 표현식나 함수 등을 실행 단계에서 해당 스코프의 맨 위로 옮기는 것을 말합니다.

<br>

ES6에서는 var의 이런 단점들을 보안한 let과 const가 존재합니다.

<br>

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

```
// es6

const test = () => {
	return 'test'
}
console.log(test());

// test
```

```
// es5

var test = function test() {
	return 'test';
};
console.log(test());

// test
```

### Template Literals(템플릿 리터럴)

```
// es6

let test = 'test';

console.log(`테스트 입니다. ${first}`);

// 테스트 입니다. test
```

```
// es6

let test = 'test';

console.log('테스트 입니다. ' + test);

// 테스트 입니다. test
```

### Default parameters(기본 매개 변수)

함수에 전달된 파라미터의 값이 undefined일 때, 초기화 설정된 값을 말합니다.

```
function test(test='테스트') { 
	return test;
}

console.log(test('테스트 입니다.'))
// 테스트 입니다.

console.log(test())
// 테스트
```


### Array and object destructing(배열 및 객체 비구조화)


### Import and export(가져오기 및 내보내기)
### Promises(프로미스)
### Rest parameter and Spread operator(나머지 매개 변수 및 확산 연산자)
### Classes(클래스)