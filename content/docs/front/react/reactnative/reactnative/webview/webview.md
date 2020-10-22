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