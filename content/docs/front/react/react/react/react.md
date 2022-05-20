---
slug: index
date: 2022-05-16
title: "React(리액트)"
description: "React는 SPA(Single Page Application) 개발 방식을 전제로 하고 있습니다. 프레임워크가 아닌 라이브러리로서 통상적으로 사용되는 라우팅, API통신 등의 기능들이 기본적으로 제공되지 않기 때문에 추가적으로 라이브러리를 사용해야 합니다. 최근엔 Next.js와 같은 라이브러를 통해 프레임워크 같이 사용할 수 있습니다. React의 동작방식은 render시 Virtual DOM에서 Dirty Checkking을 통해서 업데이트 해야하는 DOM 요소를 찾아내고 해당 부분만 업데이트 합니다. 이러한 기능으로 빠른 퍼포먼스가 특징입니다. React는 페이스북, 인스타그램 등과 같이 보여지는 데이터의 잦은 변경이 이루어지는 웹에 사용했을 때 효율이 극대화 됩니다. 단, 데이터가 자주 변경되지 않는 비교적 정적인 웹 페이지에 이를 적용할 경우 오히려 성능면에서 손해를 볼 수 있습니다."
toc: true
---

## 이해하기

React는 SPA(Single Page Application) 개발 방식을 전제로 하고 있습니다.

프레임워크가 아닌 라이브러리로서 통상적으로 사용되는 라우팅, API통신 등의 기능들이 기본적으로 제공되지 않기 때문에
<br>
추가적으로 라이브러리를 사용해야 합니다.

최근엔 Next.js와 같은 라이브러를 통해 프레임워크 같이 개발환경을 구축할 수 있습니다.

<br>

React의 동작방식은 화면을 렌더링 할 때 Virtual DOM에서 Dirty Checkking을 통해서 업데이트 해야하는 DOM 요소를 찾아내서
<br>
해당 부분만 업데이트 합니다. 이 기능이 react에 가장 큰 특징이라고 할 수 있습니다.

<br>

React는 페이스북, 인스타그램 등과 같이
<br>
보여지는 데이터의 잦은 변경이 이루어지는 웹에 사용했을 때 효율이 좋습니다.

단, 데이터가 자주 변경되지 않는 비교적 정적인 웹 페이지에 이를 적용할 경우엔
성능면이나, 구현하는 공수 등 분리한 점도 있습니다.

<br>

**React는 변경이 있는 DOM요소만 찾아서 업데이트 하는 기능이 특징이며
<br>페이스북, 인스타 등 화면의 요소 변경이 자주 있는 웹에 최적화 된 라이브러리 입니다.**

### Virtual DOM(가상 DOM)

React에서 컴포넌트가 반환하는 엘리먼트들을 내부적으로 트리 형태로 관리하고 표현합니다.

내부적으로 변경이 필요한 부분만 찾도록 구현되어 있어 DOM 노드를 최소화 하고 있는데
<br>
이런 구조를 Virtual DOM 이라고 합니다.


### Dirty Checkking(더티 체킹)

React는 컴포넌트의 Props나 State의 변경이 있을 때
<br>
컴포넌트의 이전 엘리먼트들과 새로운 엘리먼트들을 비교하여 변경점에 대해서만 업데이트 합니다.

이를 좀 더 구조적으로 설명하자면
<br>
메모리 데이터 구조 캐시를 만들고 결과 차이를 계산한 다음 브라우저에 표시되는 DOM을 효과적으로 업데이트 합니다.

이런 상태변화를 검사하는 것을 Dirty Checking이라고 합니다.


## 시작하기

### 리액트 설치 (yarn)
```
$ yarn global add create-react-app
```

### 리액트 프로젝트 생성
```
$ create-react-app react-project
```

### 리액트 서버 실행 / localhost:3000
```
cd react-project
yarn start
```

## 리엑트 설정

### 불필요 파일 제거
```
App.css
App.js
App.test.js
index.css
logo.svg
```

### nodemon
코드를 변경할 때 마다 서버를 자동으로 재시작 한다.
```
$ yarn add --dev nodemon
```

### NODE_PATH

Window 환경은 cross-env 설치 후 명령어 앞에 붙여줌.
```
$ yarn add --dev cross-env
```

방법1
```
.env

NODE_PATH=src
```

방법2
```
package.json - script

"scripts": {
    "start": "cross-env NODE_PATH=src react-scripts start",
    src/index.js"
}
```

이후 아래와 같이 import 및 경로 불러올 시에 src이하 경로로 인식

```
import App from 'components/App';
```