---
weight: 1
slug: index
title: "서버 구성"
toc: true
---

## 기본 구성

![01](/docs/infra/aws/ec2/autoscaling/configuration/01.png)

AWS Auto Scaling(오토 스케일링) 그룹은 AWS에서 제공하는 자동 다중 서버 서비스다.
같은 사양, 환경, 코드를 가진 똑같은 EC2 인스턴스 묶음이며, AMI를 이용하기 때문에 같은 서버를 구성할 수 있다.

서비스에 사용자가 몰리는 경우에 따라 서버의 수를 자동으로 늘리고 줄이기 때문에
24시간 켜놓는 것보다 비용절감이 된다.

## 오토 스케일링 응용

![02](/docs/infra/aws/ec2/autoscaling/configuration/02.png)

### CPU 사용량 정책
예를 들어, 평균 CPU 사용량 정책에 따라 평소 EC2 3대로 요청을 처리하다가,
사용량이 많아져 10분동안 평균 CPU 사용량이 90% 이상을 넘어서면, Auto Scaling그룹에서
똑같은 EC2를 증설한다. 그럼 정상 CPU 사용량으로 맞춰질 수 있다.

### 시간 기준 정책
에를 들어, 소셜 커머스와 같이 특정 시간에 서비스 요청이 몰린다면
사람이 매일 늘리는 것이 아니라 특정 시간에 맞춰 자동으로 늘릴 수 있다.