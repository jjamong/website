---
date: 2020-09-03
slug: index
title: "styled-components"
description: "리액트 네이티브(React Native)에 styled-components 플러그인에 대한 가이드"
toc: true
---

## 설치 및 설정

### 설치

yarn
```
$ yarn add styled-components --dev
```

npm
```
$ npm install -save styled-components
```

## 사용 방법

```
import Styled from 'styled-components/native';

const Container = Styled.View`
    flex: 1;
`
```