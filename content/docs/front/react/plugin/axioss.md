---
title: axioss
date: 2020-02-11
weight: 1
---

## axios 설치

프로미스 기반 REST API 웹 요청 라이브러리

```
$ yarn add axios
```


## axios 예제

`/api/banner` api 요청/응답 받아 컴포넌트에 렌더링 하는 예제.

```
import React, { Component } from "react";
import axios from "axios";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { datas: [] };
  }

  async componentDidMount() {
    let { data: datas } = await axios.get('/api/banner')
    this.setState({ datas });
  }

  render() {
    const { datas } = this.state;

    if (datas.length > 0) {
      return datas.map(data => {
        console.log(data)
        return (
          <div key={data.imageUrl}></div>
        );
      });
    } else {
      return <div></div>;
    }
  }
}

export default App;
```