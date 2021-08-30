---
weight: 1
slug: index
date: 2021-08-02
title: "커스텀 Document (_document.js)"
description: "리액트 > Next.js > 커스텀 Document (_document.js) 가이드"
toc: true
---

## 시작하기

/pages/ 경로에 _document.js를 추가합니다.


```
// /pages/_document.js

import Document, { Html, Head, Main, NextScript } from 'next/document'

class MyDocument extends Document {
  static async getInitialProps(ctx) {
    const initialProps = await Document.getInitialProps(ctx)
    return { ...initialProps }
  }

  render() {
    return (
      <Html>
        <Head />
        <body>
          <Main />
          <NextScript />
        </body>
      </Html>
    )
  }
}

export default MyDocument
```