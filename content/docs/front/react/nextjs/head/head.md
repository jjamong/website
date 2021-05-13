---
weight: 1
slug: index
date: 2021-05-13
title: "next/head"
description: "리액트 Next.js > Head 가이드"
toc: true
---

`<Head></Head>` 컴포넌트를 사용해서 html의 `<head></head>`태그를 컨트롤 할 수 있습니다. 일반적으로는 head의 title태그나, meta태그 등을 페이지 별로 다르게 적용할 때 사용할 수 있습니다.


## 시작하기

```
// pages/index.js

import Head from 'next/head'

const Index = () => (
    <>
        <Head>
            <title>Next.js Head</title>
        </Head>
        <div>
            Next.js
        </div>
    </>
)

export default Index;
```

![head](/docs/front/react/nextjs/head/01.png)