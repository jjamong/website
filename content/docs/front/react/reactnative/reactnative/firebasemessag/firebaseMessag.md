---
weight: 1
slug: index
date: 2020-10-26
title: "Firebase Messag"
description: "리액트 네이티브(React Native) Firebase Messag 가이드"
toc: true
---

## 시작하기 (Android)

### Firebase 설정

<br>
###### 1. 프로젝트 생성
https://console.firebase.google.com/ 로 접속하여 프로젝트를 생성한다.

![프로젝트 생성](/docs/front/react/reactnative/reactnative/firebasemessag/01.png)

<br>
###### 2. Android 앱 등록 및 검토

Ansdroid 앱 등록 시 순차적으로 가이드를 따라하고
설치확인을 완료한다.

![Android 앱 생성](/docs/front/react/reactnative/reactnative/firebasemessag/02.png)

### 설치

프로젝트에서 firebase를 사용하기 위해선 @react-native-firebase/app 가
설치되어 있어야 한다.
```
$ yarn add @react-native-firebase/app
$ yarn add @react-native-firebase/messaging
```

### 예제

index.js
```
import { AppRegistry } from 'react-native';
import messaging from '@react-native-firebase/messaging';
import App from './App';
import {name as appName} from './app.json';

messaging().setBackgroundMessageHandler(async remoteMessage => {
  console.log('백그라운드 및 앱 종료 시 푸쉬!', remoteMessage);
});

AppRegistry.registerComponent(appName, () => App);
```

app.js
```
import React, { useEffect } from 'react';
import {Text} from 'react-native';
import messaging from '@react-native-firebase/messaging';

const App: () => React$Node = () => {
  useEffect(() => {
    const unsubscribe = messaging().onMessage(async remoteMessage => {
      console.log('앱 실행 시 푸쉬!', remoteMessage);
    });

    return (unsubscribe);
  });

  return(
    <Text>푸쉬</Text>
  );
}

export default App;
```

코드 작성 및 앱을 실행하고 firebase에서 메시지를 보낸다.

1.알림
![메시지 보내기](/docs/front/react/reactnative/reactnative/firebasemessag/03.png)

2.타겟
![메시지 보내기](/docs/front/react/reactnative/reactnative/firebasemessag/04.png)

3.예약
![메시지 보내기](/docs/front/react/reactnative/reactnative/firebasemessag/05.png)

4.게시
![메시지 보내기](/docs/front/react/reactnative/reactnative/firebasemessag/06.png)


### 결과

앱 실행시킨 상태로 메시지를 보내면 아래와 같은 로그가 발생하고,
![로그](/docs/front/react/reactnative/reactnative/firebasemessag/07.png)

앱 백그라운드 상태나, 종료후 메시지를 보내면 아래와 같은 로그가 발생한다.
![로그](/docs/front/react/reactnative/reactnative/firebasemessag/08.png)