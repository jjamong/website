---
weight: 1
slug: index
title: "그룹 생성"
toc: true
---

## 1. AMI 생성

`위치` : 서비스의 [인스턴스] > [인스턴스]

인스턴스 중지 상태에서, 이미지 -> 이미지 생성을 선택한다.

![01](/docs/infra/ec2/autoscaling/groupcreate/01.png)

- - - 

이미지 이름을 입력하고 이미지 생성 버튼을 선택한다.

![02](/docs/infra/ec2/autoscaling/groupcreate/02.png)

- - - 

이미지 생성 성공 화면이며, 보유 중인 이미지를 선택하면 이미지 목록으로 이동한다.

![03](/docs/infra/ec2/autoscaling/groupcreate/03.png)

- - - 

`위치` : 서비스의 [이미지] > [AMI]

그림과 같이 상태가 available로 표기 되면 생성이 완료된 것이다.

![04](/docs/infra/ec2/autoscaling/groupcreate/04.png)

- - - 

## 2. 시작 템플릿 생성

`위치` : 서비스의 [인스턴스] > [시작 템플릿]

- 시작 템플릿 이름 : ec2-launch-template
- AMI ID : Search for AMI를 선택하여 생성한 AMI를 선택한다.
- 키 페어 이름 : 생성한 키 페어를 선택한다.
- 시작 템플릿 보안 그룹 : 생성한 보안 그룹을 선택한다.

![05](/docs/infra/ec2/autoscaling/groupcreate/05.png)

- - - 

필수값을 다 입력하였으면, 시작 템플릿 생성을 선택한다.

![06](/docs/infra/ec2/autoscaling/groupcreate/06.png)

- - - 

시작 템플릿 성공. 보유 중인 시작 템플릿 목록으로 이동한다.

![07](/docs/infra/ec2/autoscaling/groupcreate/07.png)

- - - 

## 3. Autu Scaling 그룹 생성

`위치` : 서비스의 [AUTO SCALING] > [Autu Scaling 그룹]

시작 템플릿을 설정하면 아래 시작 템플릿 목록이 보이며
목록에 노출된 템플릿을 선택하고 다음단계를 선택한다.

![08](/docs/infra/ec2/autoscaling/groupcreate/08.png)

### 3.1. Auto Scaling 그룹 세부 정보 구성

Auto Scaling 그룹의 설정을 지정하는 화면이다.
그룹 명, 관리할 인스턴스 최초 수를 지정 할 수 있다.

서브넷은 인스턴스들을 어떤 네트워크 망에 띄울 것인지를 정하는 것인데,
그림과 같이 2a, 2c를 기본값으로 모두 지정한다. 이는 생성하는 인스턴스들 중 절반은 서울 리전 a 가용 영역에, 다른 절반은 서울 리전 c 가용 영역에 생성한다는 의미다.

![09](/docs/infra/ec2/autoscaling/groupcreate/09.png)

### 3.2. 조정 정책 구성

Auto Scaling 그룹 내 인스턴스 수를 자동으로 조절하기 위한 조정 정책을 설정하는 화면이다.

최소 1대에서 최대 2대의 인스턴스를 실행하게 해서 평균 CPU 사용률이 80%를 기준으로 설정한다.

![10](/docs/infra/ec2/autoscaling/groupcreate/10.png)

### 3.3. 알림 구성

Auto Scaling 그룹 내 인스턴스 변화가 있을 경우 알림 설정한다.

![11](/docs/infra/ec2/autoscaling/groupcreate/11.png)

### 3.4. 태그 구성

Auto Scaling 그룹 내 인스턴스 태그를 설정한다.

![12](/docs/infra/ec2/autoscaling/groupcreate/12.png)


### 3.5. 검토

Auto Scaling 그룹 설정값들을 검토하는 화면이다.

![13](/docs/infra/ec2/autoscaling/groupcreate/13.png)

### 3.6. 완료

생성이 완료되면 그림과 같이 리스트 목록에 출력된다.

![14](/docs/infra/ec2/autoscaling/groupcreate/14.png)
