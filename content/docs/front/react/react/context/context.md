---
slug: index
date: 2020-10-18
title: "Context"
description: "리액트(React) Context API 가이드"
toc: true
---

## context api
React 16.3 부터는 Context API를 이용해서 트리의 모든 레벨에 값을 공유하는 방법을 제공한다.

일반적으로 부모와 자식간 props, state를 변화시키는 방법을 기반하여 
이전엔 redux와 같은 것들을 통해서 부모 자식간 컴포넌트 전달이 아닌 전체 컴포넌트에 값 전달 하는 방법을 사용했다.

Context API는 `createContext`, `Provider`, `Consumer` 3가지 기능으로
redux 기능을 대신할 수 있다.

### API

### createContext
사용 예제
```
import React from "react";

const ReactContext = React.createContext(null);
export default ReactContext;
```

### Context.Provider
사용 예제
```
import Store from "ReactContext";

...

render() {
    const { test } = this.state;
    return (
      <ReactContext.Provider value={this.state}>
        <Test test={test} />
        <Test2 />
        <Test3 />
      </ReactContext.Provider>
    );
  }
```

### Context.Consumer
사용 예제
```
render() {
    const { test } = this.props;
    return (
      <div>
        <ReactContext.Consumer>{(store) => store.message}</ReactContext.Consumer>
        <ReactContext.Consumer>
          {(store) => (
            <button type="button" onClick={store.changeContext}>
              changeContext
            </button>
          )}
        </ReactContext.Consumer>
        {test}
      </div>
    );
  }
```