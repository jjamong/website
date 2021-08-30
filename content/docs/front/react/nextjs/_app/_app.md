---
weight: 1
slug: index
date: 2021-08-02
title: "커스텀 App (_app.js)"
description: "리액트 > Next.js > 커스텀 App (_app.js) 가이드"
toc: true
---

Next.js의 _app.js 페이지는 모든 페이지의 최상위 컴포넌트입니다
_app.js 페이지에 적용할 공통 레이아웃의 역할을 합니다.

## 시작하기

/pages/ 경로에 _app.js를 추가합니다.

```
// /pages/_app.js

// import App from 'next/app'

function MyApp({ Component, pageProps }) {
  return (
    <>
      <Component {...pageProps} />
    </>
  )
}

// Only uncomment this method if you have blocking data requirements for
// every single page in your application. This disables the ability to
// perform automatic static optimization, causing every page in your app to
// be server-side rendered.
//
// MyApp.getInitialProps = async (appContext) => {
//   // calls page's `getInitialProps` and fills `appProps.pageProps`
//   const appProps = await App.getInitialProps(appContext);
//
//   return { ...appProps }
// }

export default MyApp
```