---
weight: 1
slug: index
title: "ELB 테스트"
toc: true
---

테스트 방법은 ELB에 속한 Auto Scaling 그룹을 2개 인스턴스가 동작하도록
설정후에, 한대의 웹서버를 stop하고, elb URL 로 접속/새로고침 시마다 ec2 웹사이트 상태를 확인한다.

# 1. Auto Sacling 그룹 EC2 2대 설정

`위치` : 서비스의 [AUTO SCALING] > [Auto Scaling 그룹]

편집을 통해 목표용량, 최소, 최대를 2대로 설정한다.

![01](/docs/infra/ec2/elb/test/01.png)

# 2. 대상 그룹 설정 변경

`위치` : 서비스의 [로드 밸런싱] > [대상 그룹]

대상 그룹의 상태 검사 편집을 통해 아래와 같이 설정한다.
경로와 상태코드를 통해 연결된 ec2 상태를 확인하므로 반드시
설정에 맞아야 한다.

![02](/docs/infra/ec2/elb/test/02.png)


# 3. 장애 상황 테스트

이후 Auto Sacling 그룹에 설정된 EC2 두대에 각 웹서버를 모두 start실행 할 경우에 2대의 웹서버로 모두 동작되는지 확인한다.(웹 접속 로그)

이후 장애 상황을 만들기 위해 EC2 1대의 웹서버를 종료한다.
종료하면 아래와 같이 종료한 웹서버에 붙어 오류가 발생된다.

![03](/docs/infra/ec2/elb/test/03.png)

그러나 ELB가 다시 정상적인 ec2로 요청을 보내 웹서버가 곧 정상동작하는 것을 확인 할 수 있다.


