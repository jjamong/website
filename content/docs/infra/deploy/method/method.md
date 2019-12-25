---
weight: 1
slug: index
title: "배포 방법"
toc: true
---

## 무중단/중단 배포

무중단, 중단 배포는 말 그대로 배포할 때 서비스를 중단할 지 안할지의 차이다.


무중단 배포는 서비스를 중지하지 않는다는 것은 사용자가 서비스를 이용하는데 아무런 지장 없이 배포를 진행하는 경우

중단 배포는 "서버 정기 점검"과 같이 사용자가 서비스를 사용하지 못하게 서비스 전체를 정지한 뒤 배포를 진행하는 경우

## 현재 위치 배포

현재 위치 배포(In-Place deployment)는 무중단 배포를 하기 위한 기법의 하나로 여러 서버를 배포할 때 새롭게 생성하거나 줄이지 않고 배포하는 방법.

### 1단계
클라이언트 요청을 2대 서버에 나누어 주고 있다. 모든 서버는 v10 버전의 애플리케이션을 서비스 하는 상태이다.
![01](/docs/infra/deploy/method/01.png)

### 2단계
배포할 서버를 로드밸런스에서 제외한다. 그리고 제외한 서버의 애플리케이션 버전을 v11로 배포한다.
![02](/docs/infra/deploy/method/02.png)

### 3단계
배포한 서버에 로드밸런스를 연동하고, v10 버전으로 서비스하는 서버를 로드밸런스에서 제외한다. 그리고 제외한 서버의 애플리케이션 버전을 v11로 배포한다.
![03](/docs/infra/deploy/method/03.png)

### 4단계
v11로 배포한 서버에 로드밸런스를 연동한다.
![04](/docs/infra/deploy/method/04.png)

이렇게 하면 클라이언트는 배포가 일어났는지도 모르게 무중단으로 v11 버전으로 배포했다.

현재 위치 배포는 무중단으로 배포할 수 있는 기법의 인스턴스를 생성할 필요가 없는 장점이 있지만, 배포 중엔 클라이언트 요청을 처리할 수 있는 인스턴스 수가 준다는 단점이 있다. 또한 여러 서버가 모두 배포되기 까지엔 구, 신 버전이 동시에 떠 있는 시간이 길다.

## 블루/그린 배포

블루/그린 배포(Blue/Green deployment)도 무중단 배포 기법중 하나로, 그룹은 대상 그룹일 수도 있고, Auto Scaling 그룹이 될 수 있다.

### 1단계
![05](/docs/infra/deploy/method/05.png)

### 2단계
![06](/docs/infra/deploy/method/06.png)

### 3단계
![07](/docs/infra/deploy/method/07.png)

### 4단계
![08](/docs/infra/deploy/method/08.png)

블루/그린 배포는 현재 위치 배포와 몇 가지 다른 장단점을 갖고 있다. 

첫번째는 구, 신버전이 동시에 떠 있는 시간을 매우 짧게 처리할 수 있다.
현재 위치 배포는 모든 인스턴스가 차례로 배포되는데 까지 비교적 많은 시간이 걸리는데 비해, 블루/그린 배포는 최신 버전의 코드를 배포한 서버들이 모두 준비되어 있으므로 짧은 시간에 로드밸런스 등록/제외로 배포를 완료 할 수 있다.

두번째로 롤백을 빠르게 할 수 있다. 현재 위치 배포는 롤백하는데도 다시 차례대로 배포하는 과정을 거치지만, 블루/그린 배포는 로드밸런스 등록/제외로 이전 버전으로 빠르게 롤백이 가능하다.

세번째로 배포 과정에서 서비스하는 인스턴스 수가 줄지 않으므로 장애 부담이 없다.

단 이 배포기법은 서비스 하는 그룹과 배포하는 그룹 모두 준비되어 있어야 하는 서버 수 증대라는 단점이 있다. 