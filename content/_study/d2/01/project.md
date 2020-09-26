---
title: 프로젝트 설정
date: 2020-01-07
weight: 2
---

## d2 프로젝트 생성

d2 프로젝트 생성 후 localhost:3000에 리액트 프로젝트 확인

[참고](/docs/front/react/)

```
$ create-react-app d2

$ cd d2
$ yarn start
```

- - -

yarn 초기화

[참고](/docs/etc/yarn/)

```
$ yarn eject
```

- - -

NODE_PATH 설정

[참고](/docs/front/react/)

```
$ yarn add --dev cross-env
```
```
.env 파일 아래 내용 추가

NODE_PATH=src
```
- - -

router 추가

[참고](/docs/front/react/setting/router/)

```
$ yarn add react-router-dom
```

- - -

sass 설정

[참고](/docs/front/react/setting/sass/)

```
$ yarn add node-sass classnames
```

설정 후 globalStyles 설정