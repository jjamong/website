---
slug: index
date: 2020-09-02
title: "react-native-make(앱 아이콘/스플래쉬)"
description: "리액트 네이티브(React Native)의 react-native-make(앱 아이콘/스플래쉬) 플러그인에 대한 가이드"
toc: true
---

react-native-make로 앱의 아이콘/스플래쉬 화면을 설정 할 수 있다.

## 설치 및 설정

[참고] https://github.com/bamlab/react-native-make

### 설치

```
// yarn
$ yarn add -D @bam.tech/react-native-make

// npm
$ npm i -D @bam.tech/react-native-make
```

### set-icon(아이콘 설정)

set-icon 설치
```
$ react-native set-icon --path path-to-image
```

아이콘 적용
path-to-image 앱 아이콘 이미지 경로
```
// ios
react-native set-icon --platform ios --path path-to-image

// android
react-native set-icon --platform android --path path-to-image
```

### set-splash(스플래쉬 설정)

set-splash 설치
```
// yarn
$ yarn add react-native-splash-screen

// npm
$ npm i react-native-splash-screen --save
```

path-to-image 스플래쉬 이미지 경로
```
react-native set-splash --path <path-to-image> --resize <[contain]|cover|center> --background "<background-color>"

// ex
react-native set-splash --path ./src/res/images/splash.png --resize center --background "#FFFFFF"
```

###### 스플래쉬 이미지가 지속되는 문제
스플래시 이미지 적용 후 실행할 경우(android) 원래 App 화면이 출력되지 않는 문제가 한가지 있다. 
hide 코드를 적용해야 스플래쉬 이미지가 없어진다.

```
import SplashScreen from 'react-native-splash-screen';

setTimeout( () => SplashScreen.hide(), 1000);
```

`SplashScreen.hide()` 이 코드를 원하는 곳에 적용해 준다. 단, 이 코드만 적용하면 너무 빨리 사라지는 문제가 있으니 적당한 시간을 적용해준다.