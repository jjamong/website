---
weight: 1
slug: index
date: 2020-10-25
title: "알림(notification)"
description: "리액트 네이티브(React Native) 로컬 알림(푸쉬) 가이드"
toc: true
---

## 시작하기 (Android)

### 설치

```
$ yarn add react-native-push-notification
```

### 설정
android/app/src/main/AndroidManifest.xml

###### 1. com.localpush 값을 자신의 패키지명 값으로 변경.
###### 2. 아래 주석 부분 코드 추가
```
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
  package="com.localpush">

    <uses-permission android:name="android.permission.INTERNET" />

    <!-- < Only if you're using GCM or localNotificationSchedule() > -->
    <uses-permission android:name="android.permission.WAKE_LOCK" />
    <permission
        android:name="com.localpush.permission.C2D_MESSAGE"
        android:protectionLevel="signature" />
    <uses-permission android:name="com.localpush.permission.C2D_MESSAGE" />
    <!-- < Only if you're using GCM or localNotificationSchedule() > -->

```

###### 3. meta-data 부분부터 코드 추가
```
<application
      android:name=".MainApplication"
      android:label="@string/app_name"
      android:icon="@mipmap/ic_launcher"
      android:roundIcon="@mipmap/ic_launcher_round"
      android:allowBackup="false"
      android:theme="@style/AppTheme">
      <activity
        android:name=".MainActivity"
        android:label="@string/app_name"
        android:configChanges="keyboard|keyboardHidden|orientation|screenSize|uiMode"
        android:launchMode="singleTask"
        android:windowSoftInputMode="adjustResize">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
      </activity>
      <activity android:name="com.facebook.react.devsupport.DevSettingsActivity" />

      <meta-data android:name="com.dieam.reactnativepushnotification.notification_channel_name"
                android:value="YOUR NOTIFICATION CHANNEL NAME"/>
      <meta-data  android:name="com.dieam.reactnativepushnotification.notification_channel_description"
                  android:value="YOUR NOTIFICATION CHANNEL DESCRIPTION"/>
      <!-- Change the resource name to your App's accent color - or any other color you want -->
      <meta-data  android:name="com.dieam.reactnativepushnotification.notification_color"
                  android:resource="@android:color/white"/>

      <!-- < Only if you're using GCM or localNotificationSchedule() > -->
      <receiver
          android:name="com.google.android.gms.gcm.GcmReceiver"
          android:exported="true"
          android:permission="com.google.android.c2dm.permission.SEND" >
          <intent-filter>
              <action android:name="com.google.android.c2dm.intent.RECEIVE" />
              <category android:name="com.localpush" />
          </intent-filter>
      </receiver>
      <!-- < Only if you're using GCM or localNotificationSchedule() > -->

      <receiver android:name="com.dieam.reactnativepushnotification.modules.RNPushNotificationPublisher" />
      <receiver android:name="com.dieam.reactnativepushnotification.modules.RNPushNotificationBootEventReceiver">
          <intent-filter>
              <action android:name="android.intent.action.BOOT_COMPLETED" />
          </intent-filter>
      </receiver>
      <service android:name="com.dieam.reactnativepushnotification.modules.RNPushNotificationRegistrationService"/>

      <!-- < Only if you're using GCM or localNotificationSchedule() > -->
      <service
          android:name="com.dieam.reactnativepushnotification.modules.RNPushNotificationListenerServiceGcm"
          android:exported="false" >
          <intent-filter>
              <action android:name="com.google.android.c2dm.intent.RECEIVE" />
          </intent-filter>
      </service>
      <!-- </ Only if you're using GCM or localNotificationSchedule() > -->

  </application>
```

### 예제

작성 및 실행 한 후 알림텍스트를 클릭 하면 알림이 출력된다.

app.js
```
import React, {Component} from 'react';
import PushNotification from 'react-native-push-notification'
import {Text, TouchableOpacity} from 'react-native';

class App extends Component{
    notify(){
      PushNotification.localNotificationSchedule({
        message: "notified",
        date: new Date(Date.now() + 60 * 1000), // in 60 secs
      });
    }
    
    render(){
        return(
          <TouchableOpacity
            style = {{hegith:100+"%", width:100+"%", flex:1, justifyContent:"center", alignItems:"center"}}
            onPress={() =>{this.notify()}}
          >
            <Text>
              알림(클릭)
            </Text>
          </TouchableOpacity>
        );
    }
}

export default App;
```