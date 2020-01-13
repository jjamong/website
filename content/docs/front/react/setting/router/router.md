---
title: Router 적용
date: 2019-12-07
weight: 1
slug: index
---

React Router는 SPA의 라우팅 문제를 보안하는 비공식이긴 하나 거의 표준처럼 사용하고 있는 라이브러리로서,  React Router를 사용하여 `location`이나 `history`와 같은 브라우저 API와 연동이 가능하다.

- - -

## 설치 명령어 

```
$ yarn add react-router-dom
```

## 내장 함수

### BrowserRouter

-- 라우팅 컴포넌트를 사용하기 위해 기본적으로 감싸 주어야 한다.

-- 오직 하나의 자식만 가질 수 있다.

```
import {BrowserRouter} from 'react-router-dom';
```