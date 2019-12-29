---
weight: 1
slug: index
title: "ELB 생성"
toc: true
---

`위치` : EC2 서비스의 [로드 밸런싱] > [로드 밸런서]에서
로드 밸런서 생성을 선택한다.

일반적은 HTTP, HTTPS과련한 Application Load Balancer를 생성한다.

![01](/docs/infra/aws/ec2/elb/elbcreate/01.png)

## 1. 로드 밸런서 구성

로드 밸런서의 이름, 리스너, 가용 영역을 지정한다.

![02](/docs/infra/aws/ec2/elb/elbcreate/02.png)

## 2. 보안 설정 구성

HTTPS를 추가하지 않으면 경고창이 출력된다.
무시할 수 있다.

![03](/docs/infra/aws/ec2/elb/elbcreate/03.png)

## 3. 보안 설정 구성

ec2에 웹서버를 설치해 두었으므로 웹 접속으로 elb를 확인할 것이다.
따라서 그와 관련된 보안 그룹을 설정한다.

![04](/docs/infra/aws/ec2/elb/elbcreate/04.png)

## 4. 라우팅 구성

로드 밸런서가 클라이언트로부터 받은 요청을 전달할 대상 그룹을 설정하는 화면이다.

현재 생성햐둔 대상 그룹이 없으면 생성한다.
상태검사 경로로 설정한 URL로 주기적으로 요청하고 상태코드 200을 받는지 확인한다.

![05](/docs/infra/aws/ec2/elb/elbcreate/05.png)

## 5. 대상 등록

Auto Scaling에서 생성된 인스턴스가 한대 보인다.

![06](/docs/infra/aws/ec2/elb/elbcreate/06.png)


## 6. 검토

위에 설정한 값들을 확인하고 생성 버튼을 선택한다.

![07](/docs/infra/aws/ec2/elb/elbcreate/07.png)


## 7. 대상 그룹 설정

`위치` : EC2 서비스의 [AUTO SACLING] > [Auto Scaling 그룹]

목록에 오토 스케일링 그룹의 세부정보(위에서 생성한 대상 그룹)를 수정한다.

![08](/docs/infra/aws/ec2/elb/elbcreate/08.png)

## 8. 대상 그룹 설정

`위치` : EC2 서비스의 [로드 밸런싱] > [대상 그룹]

대상 탭에 편집 버튼을 선택하여 아래와 같이 오토 스케일링 인스턴스를
추가한다.

![09](/docs/infra/aws/ec2/elb/elbcreate/09.png)


## 8. 로드 밸런서 설정 확인

`위치` : EC2 서비스의 [로드 밸런싱] > [로드 밸런서]

설명 탭에 있는 DNS 이름을 통해 web으로 접속한다.
아래와 같이 출력되면 elb가 올바르게 동작하는 것이다.

![10](/docs/infra/aws/ec2/elb/elbcreate/10.png)



