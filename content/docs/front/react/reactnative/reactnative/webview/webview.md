---
weight: 1
slug: index
date: 2020-10-22
title: "webView"
description: "리액트 네이티브(React Native) webView 가이드"
toc: true
---

## 시작하기


### 설치

```
$ yarn add react-native-webview
```

### 예제

app.js
```
import { WebView } from 'react-native-webview'

import React from 'react';
import { WebView } from 'react-native-webview'

const App = () => {
    return (
        <WebView
            source={{ uri: "http://www.naver.com"}}
        />
    );
};

export default App;
```

## webView 통신

### 1. APP -> WEB 값 전달

(APP) app.js
```
import React from 'react';
import { WebView } from 'react-native-webview'

const App = () => {

    setTimeout(function () {
        const data = "hello";
        this.webview.postMessage(data);
    }, 2000)

    return (
        <WebView
            source={{ uri: "http://10.0.2.2/test"}}
            ref={(ref) => (this.webview = ref)}
        />
    );
};

export default App;
```

(WEB) test.html
```
<script>
	document.addEventListener("message", function(data) {
		alert(data.data)
	})
</script>
```

앱에선 postMessage를 통해 웹으로 data를 전달하며,
웹에선 addEventListener message 통해 데이터를 전달 받는다.

### 2. WEB -> APP 값 전달

(APP) app.js
```
import React from 'react';
import { WebView } from 'react-native-webview'

const App = () => {

    return (
        <WebView
            source={{ uri: "http://10.0.2.2/test"}}
            onMessage={event => {
                console.log(event.nativeEvent.data);
            }}
        />
    );
};

export default App;
```

(WEB) test.html
```
<script>
	setTimeout(function () {
		window.ReactNativeWebView.postMessage("hello!")
	}, 2000)
</script>
```

웹에서 postMessage를 통해 앱으로 data를 전달하며,
앱에선 onMessage 통해 데이터를 전달 받는다.

## 뒤로가기 버튼 앱종료 방지(android)

### 예제

안드로이드의 경우 wevView에서 뒤로가기 버튼 클릭 시 앱이 종료되는 현상이 발생한다.
따라서 아래의 코드로 뒤로가기를 방지할 수 있다.
```
const webview = useRef<WebView>(null);
  
  const onAndroidBackPress = (): boolean => {
    if (webview.current) {
      webview.current.goBack();
      return true; // prevent default behavior (exit app)
    }
    return false;
  };

  useEffect((): (() => void) => {
    BackHandler.addEventListener('hardwareBackPress', onAndroidBackPress);
    return (): void => {
      BackHandler.removeEventListener('hardwareBackPress', onAndroidBackPress);
    };
  }, []); // Never re-run this effect

  return (
    <>
      <WebView
        source={{
          uri: 'http://10.0.2.2/login'
        }}
        ref={webview}
      />
    </>
  );
```