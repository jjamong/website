---
weight: 1
slug: index
date: 2022-04-28
title: "Pattern(패턴)"
description: "스프링 프레임워크의 대표적인 디자인 패턴들을 설명합니다."
toc: true
---

<!-- 참고 : https://yomyom0824.tistory.com/85 -->

## Template Method Pattern (템플릿 메소드 패턴)

슈퍼(부모)클래스의 기능을 확장할 때 사용되는 가장 대표적인 방법으로,

슈퍼클래스에 기본적인 로직을 구성하고 그 기능의 일부를 추상 메소드나 오버라이딩 가능한 메소드 등에  세부적인 로직을 구성합니다.


```
public abstract class Parent {
    
    abstract public void detailLogic();
    
    private void baseLogic1() { ... 설정 및 전 처리(기본 로직) ... }
    private void baseLogic2() { ... 값 초기화 및 후 처리(기본 로직) ... }

    public void algorithm() {
        baseLogic1();
        detailLogic();
        baseLogic2();
    }
}
 
class Child extends Parent {
    @override
    public void detailLogic() {
        값 셋팅, DB 처리 등(상세 로직)
    }
}

```