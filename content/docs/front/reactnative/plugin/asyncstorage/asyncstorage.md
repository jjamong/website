---
weight: 1
date: 2020-09-02
title: "AsyncStorage"
description: "리액트 네이티브(React Native)에 AsyncStorage 플러그인에 대한 가이드"
toc: true
---

AsyncStorage는 앱 내에서 간단하게 데이터를 저장할 수 있는 저장소다.
웹에서 사용하는 window.localStorage와 매우 유사하다.

## 설치 및 설정

### 설치

npm
```
$ npm install -save @react-native-community/async-storage
```

## 사용

```
// set
AsyncStorage.setItem('todoList', JSON.stringify(list));

// get
const list = await AsyncStorage.getItem('todoList');
```