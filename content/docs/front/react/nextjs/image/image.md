---
weight: 1
slug: index
date: 2021-05-13
title: "next/image"
description: "리액트 Next.js > Image 가이드"
toc: true
---

## 시작하기

```
// pages/index.js

import Image from 'next/image'

const Index = () => (
    <div>
        Next.js
        <Image
            src="/next.js.png"
            width={250}
            height={250}
        />
    </div>
);

export default Index;
```

![image](/docs/front/react/nextjs/image/01.png)

## Required Props(필수 값)

##### src
url 입력
<br><br>

##### width
layout="fill" 값이 없는 경우 정수값 필요
<br><br>

##### height
layout="fill" 값이 없는 경우 정수값 필요
<br><br>