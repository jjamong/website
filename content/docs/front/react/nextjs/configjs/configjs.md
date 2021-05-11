---
weight: 1
slug: index
date: 2021-05-11
title: "next.config.js"
description: "리액트 > Next.js > next.config.js 가이드"
toc: true
---

Next.js의 환경설정 파일로 sass 모듈 등을 설정 할 수 있습니다.

## 시작하기

루트 경로에 next.config.js를 추가합니다.

```
// next.config.js

module.exports = {
    publicRuntimeConfig: {
        var: 'next.config.js test'
    }
}
```

pages 폴더에 index.js 파일을 추가합니다.
```
// pages/index.js

import getConfig from 'next/config';

const Index = () => {
    const { publicRuntimeConfig } = getConfig();

    return (
        <div>
            Next.js | {publicRuntimeConfig.var}
        </div>
    );
};

export default Index;
```

next.config.js에 설정하고, import해서 사용할 수 있습니다.

![configjs](/docs/front/react/nextjs/configjs/01.png)