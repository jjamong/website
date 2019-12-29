---
weight: 1
slug: index
title: "stress 명령어 테스트"
toc: true
---

## 1. stress 명령어 테스트

`위치` : 서비스의 [AUTO SCALING] > [Auto Scaling 그룹]

인스턴스 탭에 1개의 인스턴스가 존재한다.

현재 인스턴스에 사용량 지표를 기본 5분에 한 번 씩만 모니터링 서버로 전송하고 있다.
(기본 시간 변경 시 추가비용 발생)

![01](/docs/infra/aws/ec2/autoscaling/test/01.png)

- - -

실행 되고 있는 EC2 서버에 접속하여 명령어들 차례로 실행한다.

설치 명령어
```
$ sudo yum install stess -y
```

600초 동안 1개의 CPU 사용량을 최대로 늘린다.
```
stess --cpu 1 --timeout 600
```

5 ~ 10 분을 기다리면 인스턴스가 1대더 추가됨을 확인 할 수 있으며, 
그 이후로는 다시 정상적으로 1대로 줄어든다.

![02](/docs/infra/aws/ec2/autoscaling/test/02.png)