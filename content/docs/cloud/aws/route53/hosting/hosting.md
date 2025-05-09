---
weight: 1
slug: index
date: 2021-09-01
title: "호스팅 영역"
description: "AWS 호스팅 영역 가이드"
toc: true
---

외부 도메인을 AWS 인스턴스에 연결할 수 있도록 하는 호스팅 설정입니다.

<br>

[인스턴스](/docs/infra/aws/ec2/instancecreate/)가 실행중이어야 사용이 가능 합니다.

`위치` : Route53 > 호스팅 영역

## 1. 호스팅 영역 생성

호스팅 영역 목록을 확인 할 수 있으며, 호스팅 영역 생성 버튼을 선택합니다.

![01](/docs/infra/aws/route53/hosting/01.png)


### 정보 입력

호스팅할(구매한) 도메인을 입력하고 생성 버튼을 선택합니다.

![02](/docs/infra/aws/route53/hosting/02.png)


### 호스팅 영역 확인

생성한 호스팅 영역을 확인 할 수 있습니다.
레코드를 생성 버튼을 선택 합니다.

![03](/docs/infra/aws/route53/hosting/03.png)


## 2. 레코드 추가

호스팅할 URl이 호출될 경우 접속할 서버를 설정해 줍니다.
값에 사용중인 인스턴스 IP를 설정합니다.

![04](/docs/infra/aws/route53/hosting/04.png)


### 레코드 확인

유형 `NS`에 설정된 라우트 대상을 호스팅할 도메인의 DNS에 연결해야 합니다.

```
ns-602.awsdns-11.net.
ns-509.awsdns-63.com.
ns-1938.awsdns-50.co.uk.
ns-1380.awsdns-44.org.
```

![05](/docs/infra/aws/route53/hosting/05.png)


