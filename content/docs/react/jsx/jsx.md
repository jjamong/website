---
title: JSX
docs_home: true
date: 2019-11-10
weight: 1
slug: index
---

## JSX 문법
컴포넌트에 여러 요소가 있다면 부모 요소 하나로 꼭 감싸야 한다.
또한 br, input 등 단일 태그들도 닫아야 한다.

```
// 올바른 예
return (
<div>
    <h1>리액트 h1</h1>
    <h2>리액트 h2</h2>
    <br/>
</div>
);

// 에러 발생
return (
<h1>리액트 h1</h1>
<h2>리액트 h2</h2>
<br>
);
```

## 자바스크립트 표현법

```
const text = "리액트 h2";
const check = true;
const style = {
width: '100px',
height: '100px',
background: 'red'
};

return (
<div>
    <h1>리액트 h1</h1>
    <h2>{text}</h2>
    {check ? '참' : '거짓'}
    <div style={style}></div>
    <div className="test"></div>
</div>
);
```

## 주석
```
return (
<div>
    <div
      // 주석
      /* 주석 */
      />
</div>

// <div></div>
);
```
