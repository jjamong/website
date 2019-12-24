---
title: props
date: 2019-11-10
weight: 1
---
props는 properties를 줄인 것으로, 컴포넌트 속성을 설정한다.


## props 값 설정

### props 값 설정 디폴트 값
```
class MyComponent extends Component {
    static defaultProps = {
        name: 'props1'
    }

    render() {
        return (
            <div>
                MyComponent {this.props.name}
            </div>
        );
    }
}

// MyComponent props1
```

### props 값 설정 값 재정의
```
class MyComponent extends Component {
    static defaultProps = {
        name: 'props1'
    }

    render() {
        return (
            <div>
                MyComponent {this.props.name}
            </div>
        );
    }
}

MyComponent.defaultProps = {
    name: 'props2'
}

// MyComponent props2
```


### JSX 내부에서 값 설정
```
function App() {

  return (
    <div>
      <MyComponent name="props3" />
    </div>
  );
}

// MyComponent props3
```