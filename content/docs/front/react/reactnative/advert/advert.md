---
slug: index
date: 2020-10-29
title: "광고(advert)"
description: "리액트 네이티브(React Native)의 광고(advert) 가이드"
toc: true
---

firebase 프로젝트 생성 및 App 설정을 선행해야 한다.

[파이어베이스(firebase)](/docs/front/react/reactnative/firebase/) 

## 광고 생성

<br>

### 1. 광고 앱 설정

프로젝트 생성 후 아래 AdMob 선택한다.

![광고 생성01](/docs/front/react/reactnative/advert/01.png)

<br>

AdMob 가입 후 계정에 카드연결 등 완료하면 아래와 같은 화면이 나온다.
AdMob에서 앱 연결을 선택한다.
![광고 생성02](/docs/front/react/reactnative/advert/02.png)

<br>

앱 추가 버튼을 선택한다.
![광고 생성03](/docs/front/react/reactnative/advert/03.png)

<br>

앱 상태 확인 후 선택.
![광고 생성04](/docs/front/react/reactnative/advert/04.png)

<br>

패키지 앱 명을 입력한다.
![광고 생성05](/docs/front/react/reactnative/advert/05.png)

<br>

광고 앱이 생성되었다. 이제 광고를 생성하기 위해 버튼을 선택한다.
![광고 생성06](/docs/front/react/reactnative/advert/06.png)
- - -

### 2. 광고 생성

<br>

배너광고를 선택한다.
![광고 생성07](/docs/front/react/reactnative/advert/07.png)

<br>

광고이름을 입력하고 만들기를 선택한다.
![광고 생성08](/docs/front/react/reactnative/advert/08.png)

<br>

광고 생성 완료.
![광고 생성09](/docs/front/react/reactnative/advert/09.png)

## 광고 게재

### 1. 광고 라이브러리 설치

```
yarn add @react-native-firebase/app
yarn add @react-native-firebase/admob
```

@react-native-firebase/admob 를 설치 하고 앱을 실행하면
아래와 같은 에러가 발생한다.

![광고 게재10](/docs/front/react/reactnative/advert/10.png)

해결법은 아래 코드를 적용한다.

android\app\build.gradle
```
android {

    defaultConfig {
           
        multiDexEnabled true
    }
}
```

- - -

### 2. firebase.json 파일 추가

<'root-project'>/firebase.json
```
{
    "react-native": {
      "admob_android_app_id": "ca-app-pub-7477469941918445~3939300344",
      "admob_ios_app_id": "ca-app-pub-7477469941918445~3939300344"
    }
}
```

firebase.json파일이 추가되어 있지 않을 경우 앱 실행 시
비정상적으로 앱이 종료된다.

- - -

### 3. 샘플 코드

app.js
```
import React from 'react';
import { View, 
  Text, 
} from 'react-native';
import { BannerAd, BannerAdSize, TestIds  } from '@react-native-firebase/admob';
class App extends React.Component {
  render() {
    const adBannerUnitId = __DEV__ ? TestIds.BANNER : 'ca-app-pub-7477469941918445~3939300344'; // 광고 ID 입력 

    return (
        <View>
            <Text> AD Text </Text>
            <BannerAd
                unitId={adBannerUnitId}
                size={BannerAdSize.FULL_BANNER}
                requestOptions={{
                    requestNonPersonalizedAdsOnly: true,
                }}
                onAdLoaded={() => {
                    console.log('Advert loaded');
                }}
                onAdFailedToLoad={(error) => {
                    console.error('Advert failed to load: ', error);
                }}
            />
        </View>
    );
  }
}
export default App
```

<br>

광고 게재 완료

![광고 게재11](/docs/front/react/reactnative/advert/11.png)