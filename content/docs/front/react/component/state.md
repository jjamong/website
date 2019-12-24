---
weight: 1
title: "state"
date: 2019-11-11
toc: true
---

props는 보통 부모 컴포넌트가 설정하며, 컴포넌트 자신은 해당 props를 읽기 전용으로만 사용할 수 있다.

컴포넌트 내부에서 업데이트 할 수 있는 값을 사용하려면 state를 써야한다.

## state 설정

### JSX 내부에서 값 설정

setState 함수로 state값을 업데이트 한다.

```
class MyComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            number: 0
        }
    }

    render() {
        return (
            <div>
                <div>숫자 : {this.state.number}</div>
                <button onClick={()=> {
                    this.setState({
                        number: this.state.number + 1
                    })
                }}
                >더하기</button>
            </div>
        );
    }
}

// 버튼 세번 클릭 시
// 숫자 : 3
```
