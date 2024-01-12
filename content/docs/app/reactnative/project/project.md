---
weight: 1
slug: index
date: 2024-01-12
title: "프로젝트 시작하기"
description: "리액트 네이티브(React Native)에 프로젝트 시작하기"
toc: true
---

## 1. 환경 셋팅
	
#### 1.1 자바 17
MacOS에서는 zsh, bash shell에 각각 JAVA_HOME 적용 및 확인

```
$ java -version
java version "17.0.9" 2023-10-17 LTS
```

#### 1.2 Ruby 3.0.0 `MacOS`
```
$ ruby -versions
ruby 3.0.0p0 (2020-12-25 revision 95aff21468) [arm64-darwin22]

$ rbenv versions
  system
* 3.0.0 (set by /Users/ios/.rbenv/version)
```

#### 1.3 yarn
`MacOS` 이슈 : npm으로 react native 설치 후 init시 버전 이슈로 최신 버전으로 생성이 안됨.
```
$ sudo npm i -g yarn
```

#### 1.4 React Native CLI
리액트 네이티브 설치
```
$ yarn global add react-native-cli
```

<br>	
## 2. 프로젝트 셋팅

####	2.1 프로젝트 생성
```
$ sudo npx react-native init JJAMONG_APP --version 0.73.0
Do you want to install CocoaPods now? - n
```

####	2.2 폴더 권한 변경 `MacOS`
프로젝트를 sudo로 생성했기에 모든 폴더 권한 허용으로 변경
<br>JJAMONG_APP 폴더 > 정보 가져오기 > 자물쇠해제 > everyone, staff 읽기 및 쓰기로 권한 변경 > ... 선택 > 하위 항목에 적용

#### 2.3 코코아포드 설치 `IOS`

```
$ cd ios
$ pod install
```

pod install이 오류 발생 할 경우 아래의 명령어로 해결 됨. 최초 이후 발생 안함.
<br>(https://velog.io/@cullen/React-Native-초기-설정-오류Pod-install)
```
sudo xcode-select --switch /Applications/Xcode.app
```

#### 2.4 No bundle URL present. ~ .jsbundle ~ `IOS`

Xcode로 불러온 다음 실행 시 No bundle URL present. ~ .jsbundle ~ 에러가 발생할 경우 아래 명령어 입력
```
$ yarn react-native bundle --entry-file='index.js' --bundle-output='./ios/main.jsbundle' --dev=false --platform='ios'
```
이후 생성되는 main.jsbundle파일이 xcode에 자동 추가 되지 않아 프로젝트 상단에 드롭다운
<br>(https://velog.io/@haerim95/React-Native-Error-Log-No-bundle-URL-present-이슈)

#### 2.5 ~ (run 'react-native start') or that your bundle 'index.android.bundle' ~ `AOS`
	
/JJAMONG_APP/android/app/src/main 경로에 assets 폴더 추가
<br>추가 이후 아래 명령어 입력
<br>(https://borntodevelop.tistory.com/entry/React-Native-Error-Unable-to-load-script-Make-sure-youre-either-running-a-Metro-server-run-react-native-start-or-that-your-bundle-indexandroidbundle-is-packaged-correctly-for-release)
```
yarn react-native bundle --platform android --dev false --entry-file index.js --bundle-output android/app/src/main/assets/index.android.bundle
```

#### 2.6 패키지명 변경, 폴더변경

##### AOS
com.jjamong_app > com.jjamong.app 로 변경
<br>com/jjamong_app 폴더 아래의 파일들 com/jjamong/app 폴더 아래로 추가

##### IOS
Xcode JJAMONG_APP ROOT 선택 > Siging & Capabilites > All > Team > DA Information. Co. Ltd 선택
<br>Xcode JJAMONG_APP ROOT 선택 > Siging & Capabilites > All > Bundle Identifier > com.jjamong.app 입력

#### 2.7 앱 이름 변경

##### AOS
```
/JJAMONG_APP/android/app/src/main/res/values/string.xml

<resources>
    <string name="app_name">자라다</string>
</resources>
```
##### IOS
```
/JJAMONG_APP/ios/JJAMONG_APP/info.plist

Information Property List > Bundle display name > 자라다 입력
```

#### 2.8 앱 아이콘 변경

앱 아이콘 이미지는 앱 제작 및 리사이징 사이트 등을 통해 aos, ios에 맞게 이미지를 준비합니다.

##### AOS
/JJAMONG_APP/android/app/src/main/res 아래 폴더(hdpi, mdpi, xhdpi, xxhdpi, xxxhdpi)에 아이콘 이미지 변경

##### IOS
Xcode에서 JJAMONG_APP > JJAMONG_APP > Images 선택
<br>AppIcon안에 이미지 사이즈에 맞게 드롭다운

#### 2.9 스플래시 빈 화면으로 변경 `IOS`
<br>Xcode에서 JJAMONG_APP > JJAMONG_APP > LaunchScreen 선택
화면에 출력되는 텍스트들 제거 후 저장
	
#### 2.10 푸시를 위한 config 파일 추가

##### AOS
google-services.json 파일 /JJAMONG_APP/android/app/ 경로에 추가
		
##### IOS
GoogleService-Info.plist 파일 /JJAMONG_APP/ios/JJAMONG_APP/ 경로에 추가 후
<br>Xcode JJAMONG_APP > JJAMONG_APP 경로에 드롭다운

##### 2.10 Xcode 푸시 설정 `IOS`

Xcode JJAMONG_APP ROOT 선택 > Siging & Capabilites > + Capability 선택 > Push Notifications 선택

Xcode JJAMONG_APP ROOT 선택 > Siging & Capabilites > + Capability 선택 > Background Modes 선택
<br>Remote notifications 체크

<br>

## 3. 플러그인 셋팅
		
### 3.1 웹뷰

#### 3.1.1 플러그인 설치
```
$ sudo yarn add react-native-webview@13.6.3
```
`ios`일 경우 pod install

#### 3.1.2 코드 추가 `AOS`

AOS에서 webview의 alert이 실행되면 alert창 title에 기본적으로 web url이 출력됩니다.
<br>아래 코드를 추가하면 webview의 alert 기본 타이틀을 빈값으로 설정합니다.

##### RNCWebChromeClient (/JJAMONG_APP/node_modules/react-native-webview/android/src/main/java/com/reactnativecommunity/webview)
```
// /JJAMONG_APP/node_modules/react-native-webview/android/src/main/java/com/reactnativecommunity/webview

@Override
public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
  new AlertDialog.Builder(this.mWebView.getThemedReactContext())
      .setTitle("")
      .setMessage(message)
      .setPositiveButton("확인", new AlertDialog.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          result.confirm();
        }
      }).setCancelable(false).create().show();
  return true;
}

@Override
public boolean onJsConfirm(WebView view, String url, String message, final android.webkit.JsResult result) {
  new AlertDialog.Builder(this.mWebView.getThemedReactContext())
      .setTitle("")
      .setMessage(message)
      .setPositiveButton("확인", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
          result.confirm();
        }
      })
      .setNegativeButton("취소", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
          result.cancel();
        }
      })
      .create()
      .show();

  return true;
}
```

### 3.2 푸쉬

#### 3.2.1 플러그인 설치
```
$ sudo yarn add @react-native-firebase/app@18.7.3
$ sudo yarn add @react-native-firebase/messaging@18.7.3
```
			
#### 3.2.2 코드 추가 `IOS`

#### podfile
`podfile(/JJAMONG_APP/ios/podfile)`

아래 내용 코드 config = use_native_modules! 부분 아래 추가
```
pod 'Firebase', :modular_headers => true
pod 'FirebaseCoreInternal', :modular_headers => true
pod 'FirebaseCore', :modular_headers => true
pod 'FirebaseMessaging', :modular_headers => true
pod 'GoogleUtilities', :modular_headers => true
```
코드 추가 후 pod install

#### AppDelegate.m 
`AppDelegate.m (/JJAMONG_APP/ios/JJAMONG_APP/AppDelegate.m)`

아래 코드 최상단에 추가
```
#import <Firebase.h>
```

didFinishLaunchingWithOptions 안에 추가
```
if ([FIRApp defaultApp] == nil) {
  [FIRApp configure];
}
```

(https://velog.io/@ordidxzero/rn-random-chat-connect-firebase)
			
#### 3.2.3 코드 추가 `AOS`

#### build.gradle
`build.gradle (/JJAMONG_APP/android/build.gradle)`

dependencies {} 영역에 아래 코드 추가
```
classpath("com.google.gms:google-services:4.3.14")
```

#### build.gradle
`build.gradle (/JJAMONG_APP/android/app/build.gradle)`

apply plugin: "com.android.application" 아래 코드 추가

```
apply plugin: "com.google.gms.google-services"
```

dependencies {} 영역에 아래 코드 추가
```
// Import the Firebase BoM
implementation platform('com.google.firebase:firebase-bom:31.1.0')
implementation 'com.google.firebase:firebase-messaging'
```

#### MainActivity.kt
`MainActivity.kt (/JJAMONG_APP/android/app/src/main/java/com/dainformation/zarada)`

아래 코드 추가

```
override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  askNotificationPermission()
}

// Declare the launcher at the top of your Activity/Fragment:
private val requestPermissionLauncher = registerForActivityResult(
    ActivityResultContracts.RequestPermission()
) { isGranted: Boolean ->
  if (isGranted) {
  // FCM SDK (and your app) can post notifications.
  } else {
  // TODO: Inform user that that your app will not show notifications.
  }
}

private fun askNotificationPermission() {
  // This is only necessary for API level >= 33 (TIRAMISU)
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
        PackageManager.PERMISSION_GRANTED
    ) {
      // FCM SDK (and your app) can post notifications.
    } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
      // TODO: display an educational UI explaining to the user the features that will be enabled
      //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
      //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
      //       If the user selects "No thanks," allow the user to continue without notifications.
    } else {
      // Directly ask for the permission
      requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
    }
  }
}
```

### 3.3 모달

#### 3.3.1 플러그인 설치
```
$ sudo yarn add react-native-modal@13.0.1
```

## 4. App.tsx 코드 추가 

```
import React, { useEffect, useState, useRef } from "react";
import { Alert, View, BackHandler, SafeAreaView, StyleSheet } from "react-native";
import { WebView } from 'react-native-webview'
import messaging from '@react-native-firebase/messaging';
import Modal from "react-native-modal";

// 백그라운드 푸시
messaging().setBackgroundMessageHandler(async remoteMessage => {
    console.log('[Background Remote Message]', remoteMessage)
})

// IOS푸시 허용
async function requestUserPermission() {
    const authStatus = await messaging().requestPermission();
    const enabled =
    authStatus === messaging.AuthorizationStatus.AUTHORIZED ||
    authStatus === messaging.AuthorizationStatus.PROVISIONAL;

    if (enabled) {
        console.log('Authorization status:', authStatus);
    }
}
requestUserPermission()

const App = () => {
    const webview = useRef();
    //const source = {uri: 'http://192.168.0.1:8080'}; // 로컬
    const source = {uri: 'https://jjamong.github.io'}; // 스테이징
    const [backState, setBackState] = useState({url: '', canGoBack: false})

    const [modalVisible, setModalVisible] = useState(false)
    const [modalSource, setModalSource] = useState({uri: ''})
    
    // FCM토큰 가져오기
    const getFcmToken = async () => {
        try {
            const fcmToken = await messaging().getToken();
            console.log('[FCM Token] ', fcmToken);
        } catch (error) {
            console.log('[FCM Token] error', error);
        }
    }

    useEffect(() => {
        getFcmToken()

        // 포그라운드 푸시
        messaging().onMessage(async remoteMessage => {
            console.log('[Remote Message] ', JSON.stringify(remoteMessage));
            let message = JSON.stringify(remoteMessage)
            Alert.alert("", remoteMessage.notification?.body)
        })
    }, [])

    useEffect(() => {
        // 종료 창
        function close() {
            Alert.alert("", "확인을 누르면 종료합니다.", [
                {text: "취소", onPress: () => {}, style: "cancel"},
                {text: "확인", onPress: () => BackHandler.exitApp()},
            ])
        }

        // 뒤로가기
        const backAction = (): boolean => {
            if (backState.canGoBack) {
                if (backState.url == source.uri + "/resources/index.html#/login") {
                    close();
                } else {
                    // 뒤로 갈 수 있는 상태라면 이전 웹페이지로 이동한다
                    webview.current.goBack()
                }
            } else {
                close();
            }
            return true;
        }
        
        BackHandler.addEventListener('hardwareBackPress', backAction)
        return (): void => {
            BackHandler.removeEventListener('hardwareBackPress', backAction)
        };
    }, [backState.url, backState.canGoBack])

    // WebView 메시지
    const webViewMessage = (response): void => {
        response = JSON.parse(response);
        let key = response.key;
        let data = response.data;
        
        // 나이스인증 열기
        if (key === 'niceidOpen') {
            setModalVisible(true)
            setModalSource({uri: source.uri + '/checkplus_main'})
        // 나이스인증 닫기
        } else if (key === 'niceidClose') {
            setModalVisible(false)
            webview.current.postMessage(JSON.stringify(response))
        // 주소찾기 열기
        } else if (key === 'addrSearchOpen') {
            setModalVisible(true)
            setModalSource({uri: source.uri + '/resources/daum.jsp'})
        // 주소찾기 닫기
        } else if (key === 'addrSearchClose') {
            setModalVisible(false)
            webview.current.postMessage(JSON.stringify(response))
        }
    }

    return (
		<> 
            {/* 기본 웹뷰 */}
            <SafeAreaView style={{ flex: 1 }}>
                <WebView
                    ref={webview}
                    source={source}
                    setSupportMultipleWindows={false}
                    onNavigationStateChange={(navState) => {
                        setBackState({ url: navState.url, canGoBack: navState.canGoBack })
                    }}
                    onMessage={event => {
                        webViewMessage(event.nativeEvent.data)
                    }}
                />
            </SafeAreaView>
            
            <Modal isVisible={modalVisible} transparent={true} animationType="slide" onRequestClose={() => setModalVisible(false)} 
                style={{ position:'absolute', margin: 0, height:'100%', width:'100%', zIndex:1, alignSelf:'center'}}>
                <SafeAreaView style={{ flex: 1 }}>
                    <WebView
                        source={modalSource}
                        onMessage={event => {
                            webViewMessage(event.nativeEvent.data)
                        }}
                    />
                </SafeAreaView>
            </Modal>
		</>
    );
};

export default App;
```

##### app.tsx 반영 `IOS`
react native 코드 추가 후 아래 명령어를 입력해야 xcode에 반영 됨
<br>yarn react-native bundle --entry-file='index.js' --bundle-output='./ios/main.jsbundle' --dev=false --platform='ios'
