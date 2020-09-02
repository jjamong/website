---
weight: 2
date: 2020-09-02
title: "라이프 사이클"
description: "리액트 라이프 사이클 함수 가이드"
toc: true
---

## 라이프 사이클 함수

### constructor 함수

생성자 함수로서 클래스 컴포넌트에서 State를 사용하지 않아, 초기값 설정이 필요하지 않다면 생략 가능하다.

생성자 함수를 사용할 때는 반드시 super(props) 함수를 호출하여 부모 클래스의 생성자를 호출한다.

생성자 함수는 해당 컴포넌트가 생성될 때 한 번만 호출 된다.

```
constructor(props: Props) {
    super(props);

    this.state = {
        count: props.initValue,
        error: false,
    }
}
```

### render 함수

render 함수는 클래스 컴포넌트가 렌더링 되는 부분을 정의한다.

render 함수는 부모로 부터 받는 Props 값이 변경되거나, this.setState로 State의 값이 변경되어 화면을 갱신할 필요가 있을 때마다 호출된다.

```
render() {
    const {title} = this.props;
    const {count, error} = this.state;
    return (
        <Container>
            {...}
        </Container>
    )
}
```

### getDeriveDstateFromProps 함수

getDeriveDstateFromProps 함수는 부모로 부터 받은 Props와 State를 동기화 할 때 사용된다. 

이 함수는 컴포넌트가 생성될 때 한 번 호출되며, 생성자 함수와 다르게 Props와 State를 동기화해야 하므로 Props가 변경될 때마다 호출된다.

```
static getDeriveDstateFromProps(nextProps, prevState) {
    if (nextProps.id !== prevState.id) {
        return { value: nextProps.value };
    }
    return null;
}
```

### componentDidMount 함수

클래스 컴포넌트가 처음 화면에 표시된 이후, 이 함수가 호출된다.(render 함수 처음 호출 후)

이 함수는 컴포넌트가 화면에 처음 표시된 후 한 번만 호출되므로, ajax를 통해 데이터를 습득하는 등 작업에 적합하다.

componentDidMount 함수는 부모로부터 받는 Props 값이 변경되어도, this.setState로 State 값이 변경되어도, 다시 호출되지 않는다.

```
componentDidMount(prevProps: Props, PrevState: State, snapshot: ISnapshot) {
    ...
}
```

### shouldComponentUpdate 함수

Props, State 값이 변경되었지만, 다시 화면을 그리고 싶지 않은 경우 이 함수를 사용하여 렌더링 제어를 할 수 있다.

```
shouldComponentUpdate(nextProps: Props, nextState: State) {
    return nextProps.id !=== this.props.id;
}

```

### getSnapshotBeforeUpdate 함수

Props, State 값이 변경되어 화면을 다시 그리기 위해 render 함수가 호출된 후, 실제 화면이 갱신되기 바로 직전에

### componentDidUpdate 함수

componentDidUpdate 함수는 컴포넌트가 처음 화면에 표시될 때 실행되지 않지만 Props 또는 State가 변경되어 화면이 갱신될 때 마다 호출된다.(componentDidMount 함수와 반대)

### componentWillUnmout 함수

componentWillUnmout 함수는 해당 컴포넌트가 화면에서 완전히 사라진 후 호출되는 함수다.

보통 setTimeout, setInterval 함수 해제시 사용된다.

### componentDidCatch 함수

컴포넌트의 렌더링 도중 에러가 발생하면 앱이 비정상 종료(Crash)된다.
비즈니스 로직의 try-catch 처럼 동일하게 컴포넌트 렌더링 시 예외처리를 해주는 라이프 싸이클 함수다.

```
componentDidCatch(error: Error, info.ErrorInfo) {
    this.setState({
        error: true,
    })
}
```

## 호출 순서

-- 컴포넌트가 생성될 때

constructor -> getDeriveDstateFromProps -> render -> componentDidMount

-- 컴포넌트의 Props가 변경될 때

getDeriveDstateFromProps -> shouldComponentUpdate -> render -> getSnapshotBeforeUpdate -> componentDidUpdate

-- 컴포넌트의 State가 변경될 때

shouldComponentUpdate -> render -> getSnapshotBeforeUpdate -> componentDidUpdate