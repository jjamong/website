---
weight: 1
slug: index
date: 2021-05-18
title: "AOS(Animate On Scroll Library)"
description: "리액트 AOS 가이드"
toc: true
---

스크롤 할 때 보여지는 영역에 애니메이션 효과를 줄 수 있는 라이브러리 입니다.

url : `https://michalsnik.github.io/aos/`

## 시작하기

### 설치

yarn으로 aos를 설치합니다.
```
$ yarn add aos
```

그리고 https://michalsnik.github.io/aos/ 공식사이트에서 aos를 다운로드 받습니다.(aos-master.zip)

여기서, `/aos-master/dist/aos.css`만 필요합니다.

### 예제
```
// index.js

import React, { useEffect } from 'react';
import AOS from "aos";
import "aos/dist/aos.css";

const Index = () => {

    let boxStyle = {
        width: '40%',
        height: '200px',
        fontSize: '30px',
        lineHeight: '200px',
        background: 'black',
        color: 'white',
        textAlign: 'center'
    }

    useEffect(() => {
        AOS.init({
            duration : 1000
        });
    });

    return(
        <>
          <div>
            <div>
                <p data-aos="fade-up">AOS 테스트1</p>
            </div>
            <div style={{height: '500px'}}></div>
            <div style={boxStyle}>
                <p data-aos="fade-up">AOS 테스트2</p>
            </div>
            <div style={{height: '500px'}}></div>
            <div style={boxStyle} data-aos="fade-up">
                <p>AOS 테스트3</p>
            </div>
            <div style={{height: '500px'}}></div>
          </div>
        </>
    )
};

export default Index;
```
-------------------------