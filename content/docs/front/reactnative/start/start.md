---
weight: 1
date: 2020-08-28
title: "React Native 시작"
description: "리액트 네이티브(React Native)에 대해 설치 부터 실행하는 가이드"
toc: true
---

## 개발 환경 설정

### IOS
-

### 윈도우
윈도우에서 리액트 네이티브를 개발하기 위해선
노드(Node), 파이썬(Python), 안드로이드 스튜디오 등을 설치해야 한다.

리액트 네이티브는 ios, 안드로이드(android) 각각 개발 툴을 이용하여 배포하므로
윈도우 환경에선 ios 모바일 앱은 배포가 불가능 하다. (ios의 경우 맥(mac) OS의 Xcode 툴이 반드시 필요하다.)

#### 안드로이드

###### 1. 안드로이드 스튜디오 설치

###### 2. 안드로이드 SDK 설치
- - 2.1 Tools > SDK Manager.
- - 2.2 SDK Platforms 탭에서 원하는 플랫폼 선택 후 설치
- - 2.3 SDK Tools 탭에서 Google Play Licensing Library 설치

###### 3. Viturl Device 설치
 버추얼 디바이스를 사용하여 개발 할 경우 설치

- - 2.1 Tools > AVD Manager.

###### 4.ANDROID_SDK_ROOT 환경 변수 설정

![ANDROID_SDK_ROOT](/docs/front/reactnative/start/01.png)

###### 5. adb 환경 변수 설정

![adb](/docs/front/reactnative/start/02.png)


### 리액트 네이티브 설치

```
$ yarn global add react-native-cli
```


## 프로젝트 생성 및 실행

#### 프로젝트 생성
```
$ react-native init <프로젝트명>
```

#### 애뮬레이터 실행

안드로이드

```
$ react-native run-ios
```

ISO 

```
$ react-native run-android
```

## 구조

`SafeAreaView`
아이폰 X와 같은 노치 디자인(Notch Design)에서 상단에 상태 바와 하단에 홈 버튼의 영역을 제외한 영역에 콘텐츠를 표시할 때 사용하는 컴포넌트


`tsconfig.json`
```
{
  "compilerOptions": {
    "target": "es6", //빌드 결과물을 es6 방식을 한다는 뜻
    "module": "commonjs", // 빌과의 모듈 방식을 commonjs 방식을 한다는 뜻
    "moduleResolution": "node",//모듈 해석 방식은 node 처럼 한다는 뜻
    "sourceMap": true, // .map.js 파일도 함께 생성한다는 뜻
    "emitDecoratorMetadata": true, /* Enables experimental support for ES7 decorators. */
    "experimentalDecorators": true,  /* Enables experimental support for emitting type metadata for decorators. */
    "removeComments": false, /* Do not emit comments to output. */
    "noImplicitAny": false // 암시적으로 선언되었는데 any로 추론되면 에러를 알려준다.
  },
  "exclude": [ // npm 으로 설치한 모듈들을 import 가능하도록 설정
    "node_modules"
  ]
}
```