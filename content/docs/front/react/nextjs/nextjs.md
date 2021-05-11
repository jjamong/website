---
weight: 1
slug: index
date: 2021-05-06
title: "Next.js"
description: "리액트 Next.js 가이드"
toc: true
---

## 시작하기

`https://nextjs.org/`

Next.js는 라우트에 해당된 파일들을 소문자로 pages 폴더에 추가해야 하는 정형화된 구조를 따르게 됩니다. 


### 프로젝트 설정 및 next 설치

프로젝트를 위한 폴더를 추가하고, react와 next를 설치합니다.
```
$ yarn init -y
$ yarn add react react-dom next
```

package.json 파일에 아래 내용을 추가합니다.
```
// package.json

...
"scripts": {
    "dev": "next"
},
...

```

루트 경로에 pages 폴더를 추가하고 폴더 경로에 index.js파일을 추가합니다.
```
// index.js

const Index = () => (
    <div>
        Next.js
    </div>
);

export default Index;
```

### 실행

아래 명령어를 통해 실행합니다.
```
$ yarn run dev
```

![Next.js시작하기](/docs/front/react/plugin/nextjs/01.png)

## 페이지 라우팅

### 시작하기

components 폴더를 추가하고 Header.js, Layout.js를 추가합니다.

```
// components/Header.js

import Link from 'next/link';

const style = {
    marginLeft: '1rem'
}
const Header = () => {
    return (
        <div>
            <Link href="/">index</Link>
            <Link href="/test1"><a style={style}>test1</a></Link>
            <Link href="/test2"><a style={style}>test2</a></Link>
        </div>
    );
};

export default Header;
```

```
// components/Layout.js

import Header from './Header';

const Layout = ({children}) => (
    <div>
        <Header/>
        {children}
    </div>
);

export default Layout;
```

pages 폴더에 index.js 파일을 수정하고, test1.js, test2.js를 추가합니다.

```
// pages/index.js

import Layout from '../components/Layout';

const Index = () => (
    <Layout>
        <div>
            Next.js
        </div>
    </Layout>
);

export default Index;
```

```
// pages/test1.js

import Layout from '../components/Layout';

const Test1 = () => (
    <Layout>
        Test1
    </Layout>
);

export default Test1;
```

```
// pages/test2.js

import Layout from '../components/Layout';

const Test2 = () => (
    <Layout>
        Test2
    </Layout>
);

export default Test2;
```

실행하게 되면 아래와 같이 작동합니다.

url : `/`

![라우터02](/docs/front/react/plugin/nextjs/02.png)

<br>

url : `/test1`

![라우터03](/docs/front/react/plugin/nextjs/03.png)

<br>

url : `/test2`

![라우터04](/docs/front/react/plugin/nextjs/04.png)

### Pathname 파라미터
