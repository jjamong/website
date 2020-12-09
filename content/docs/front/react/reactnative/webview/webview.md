---
weight: 1
slug: index
date: 2020-10-22
title: "웹뷰(webView)"
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

webview 사용시 android, ios의 브라우저에 따라 message이벤트를 가지고 있는 객체가 다르다.

|디바이스 | 객체 |
|---|-----|
|android|document|
|ios|window|

디바이스 userAgent를 체크(android, ios)하여 분기하여
실행 하도록 코드를 작성하자.

<br>

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
  // android
  document.addEventListener("message", function(data) {
    alert(data.data)
  })

  // ios
  window.addEventListener("message", function(data) {
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

<br>

## 로드 표시기(스피너)

webview를 android, ios에서 모두 사용 시에
디폴트로 android는 스피너가 없지만, ios는 스피너가 존재한다.

따라서, 아래와 같이 적용할 경우 android, ios 모두 동일한 스피너가 적용된다.

- - -
### 예제

Loading 컴포넌트
```
import React from 'react';
import Styled from 'styled-components/native';

const Container = Styled.View`
  position: absolute;
  left: 50%;
  right: 0;
  top: 50%;
  bottom: 0;
  marginLeft: -40px;
  marginTop: -40px;
`;
const Image = Styled.Image`
    width: 80px;
    height: 80px;
`;

const Loading = () => {
  return (
    <Container>
        <Image source={require('~/Assets/Images/spinner.gif')}/>
    </Container>
  );
};

export default Loading;
```

webview 
```
<WebView
    ...
    startInLoadingState={true}
    renderLoading={() => <Loading />}
/>
```

###### startInLoadingState
`renderLoading`를 사용하기 위해선 true로 값을 설정해야 한다.

###### renderLoading
로딩 표시기를 불러온다.


### 결과

![스피너 결과](/docs/front/react/reactnative/webview/01.png)


