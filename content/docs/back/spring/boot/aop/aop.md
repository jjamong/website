---
weight: 1
slug: index
date: 2022-07-12
title: "AOP(Aspect Orented Programming)"
description: "AOP(Aspect Orented Programming)"
toc: true
---


### Filter(필터)

filter는 스프링 컨텍스트 외부에 존재합니다.<br>
DispatcherServlet 이전에 실행되며, 스프링과 무관하게 지정된 자원에 대해 동작한다.
  
filter는 web.xml에 등록 하는데 대표적으로<br>
인코딩 변환, 로그인 여부확인, 권한체크, XSS방어 등에 대한 처리로 사용됩니다.

init() - 필터 인스턴스 초기화<br>
doFilter() - 전/후 처리<br>
destroy() - 필터 인스턴스 종료


### Interceptor(인터셉터)

Interceptor는 스프링의 DistpatcherServlet이 컨트롤러를 호출하기 전/후로 끼어들기 때문에 스프링 컨텍스트 내부에 존재 합니다.

Interceptor는 스프링 내의 모든 객체에 접근이 가능하고 대표적으로<br>
로그인 체크, 권한 체크, 프로그램 실행 시간 계산 작업, 로그 확인, 업로드 파일처리 등에 사용됩니다.


#### preHandler()
컨트롤러 메서드가 실행되기 전에 실행됩니다.

#### postHanler()
컨트롤러 메서드 실행직 후 view 페이지 렌더링 되기 전에 실행됩니다.

#### afterCompletion()
view페이지가 렌더링 되고 난 후 실행됩니다.


### AOP(Aspect Orented Programming)

AOP는 관점 지향 프로그래밍 이라고 합니다.

AOP는 기능을 비지니스 로직과 공통 모듈로 구분한 후에 개발자의 코드 밖에서 필요한 시점에 비지니스 로직에 삽입하여 실행되도록 합니다.<br>

#### Aspect
구현하고자 하는 횡단 관심사(로깅, 트랜잭션, 권한 등)의 기능을 의미한다.
한개 이상의 포인트컷과 어드바이스의 조합으로 만들어진다.
 
#### JoinPoint
관점(Aspect)를 삽입하여 어드바이스가 적용될 수 있는 위치를 의미한다.
method를 호출하는 시점, 예외가 발생하는 시점 등과 같이 특정 작업이 실행되는 시점을 의미하기도 한다.
 
#### Advice
관점(Aspect)의 구현체로 Join Points에서 실행되어야 하는 코드(실제로 AOP 기능을 구현한 객체)
Advice는 Join Point와 결합하여 동작하는 시점에 따라 5개로 구분된다.