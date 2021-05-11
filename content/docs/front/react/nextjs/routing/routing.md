---
weight: 1
slug: index
date: 2021-05-10
title: "routing(라우팅)"
description: "리액트 Next.js > 라우팅 가이드"
toc: true
---

## 소개

기본적으로 Next.js는 pages폴더 아래로 파일 시스템 기반 라우트 기능을 가지고 있습니다.
파일이 pages폴더에 추가되면 자동으로 url 경로로 사용할 수 있게 됩니다.

### 파일과 url 매핑
라우트는 자동으로 index 이름의 파일을 폴더의 루트로 라우트 합니다.
동적 라우트의 경우 pages/posts/[id].js로 파일을 추가하면, posts/1, post/2, post/3과 같은 방식으로 사용할 수 있습니다.

```
// index 방법
pages/index.js -> '/'
pages/blog/index.js -> '/blog'

// 중첩 방법
pages/blog/first-post.js -> /blog/first-post
pages/dashboard/settings/username.js -> /dashboard/settings/username

// 동적 방법
pages/blog/[slug].js -> /blog/:slug (/blog/hello-world)
pages/[username]/settings.js -> /:username/settings (/foo/settings)
pages/post/[...all].js -> /post/* (/post/2020/id/title)

```

<br>

## Routing(라우팅)

Next.js에서 SPA와 같은 라우팅을 지원합니다. Link 컴포넌트를 이용해서 페이지 이동을 할 수 있습니다.(뒤로가기 가능)

`href` : 폴더명을 넘겨주면 됩니다.

`as` : 브라우저에 실제 표시 될 url 입니다.

### Link 컴포넌트 예제

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
            <Link href="/test2" as="/test3"><a style={style}>test2</a></Link>
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

![라우트01](/docs/front/react/nextjs/routing/01.png)

<br>

url : `/test1`

![라우트02](/docs/front/react/nextjs/routing/02.png)

<br>

url : `/test2`

`/test2` url로 접속 했지만, 실제 보여지는 url은 `/test3`입니다.
![라우트03](/docs/front/react/nextjs/routing/03.png)

<!--
https://nextjs.org/docs/routing/introduction#nested-routes
https://yceffort.kr/2020/03/nextjs-01-route
-->