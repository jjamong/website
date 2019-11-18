---
title: Component(컴포넌트)
date: 2019-11-10
weight: 1
---

## 모듈 내보내기(export)
```
// MyComponent.js
export default MyComponent;
```

## 모듈 불러오기(import)
```
import MyComponent from './MyComponent'; //

function App() {

  return (
    <div>
      <div>App</div>
      <MyComponent />
    </div>
  );
}

// App
// MyComponent
```
