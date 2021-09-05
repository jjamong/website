---
slug: index
date: 2020-10-29
title: "파이어베이스(firebase)"
description: "리액트 네이티브(React Native)의 firebase (파이어베이스) 가이드"
toc: true
---

## 프로젝트 설정

`url` https://console.firebase.google.com

<br>

### 프로젝트 생성

URL 접속 후 프로젝트 추가 버튼을 선택한다.
프로젝트 명, 애널리틱스 설정 등을 하고 프로젝트를 생성한다.

![프로젝트 생성](/docs/app/reactnative/firebase/01.png)

<br>

TEST 프로젝트 생성 완료
![프로젝트 생성](/docs/app/reactnative/firebase/02.png)

- - -

### Android APP 추가

안드로이드 버튼 선택

![프로젝트 생성](/docs/app/reactnative/firebase/03.png)

<br>

앱 패키지 명을 입력

`android/app/src/main/AndroidManifest.xml` 파일의
첫번째 라인의 패키지 명 확인
```
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
  package="com.adtest">
```

![프로젝트 생성](/docs/app/reactnative/firebase/04.png)

<br>

`google-services.json` 파일 프로젝트에 추가. (가이드 참고)
![프로젝트 생성](/docs/app/reactnative/firebase/05.png)

<br>

`android/build.gradle`, `android/app/build.gradle` 파일들에게 각각 코드 적용. (가이드 참고)
![프로젝트 생성](/docs/app/reactnative/firebase/06.png)
![프로젝트 생성](/docs/app/reactnative/firebase/07.png)

<br>

Android APP 추가 완료
![프로젝트 생성](/docs/app/reactnative/firebase/08.png)