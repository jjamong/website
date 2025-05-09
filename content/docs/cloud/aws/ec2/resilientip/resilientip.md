---
weight: 1
slug: index
date: 2021-08-31
title: "탄력적 IP"
description: "탄력적 IP 가이드"
toc: true
---

인스턴스를 생성하게 되면 IP가 유동IP로 설정됩니다. 인스턴스를 재실행 하게 되면
IP가 바뀌게 됩니다.

IP를 고정 IP로 사용할 수 있게 하는 설정이 탄력적 IP 설정입니다.

<br>

[인스턴스](/docs/infra/aws/ec2/instancecreate/)가 실행중이어야 사용이 가능 합니다.

`위치` : 네트워크 및 보안 > 탄력적 IP

## 1. 탄력적 IP 주소 할당

탄력적 IP주소 목록을 확인 할 수 있으며, 탄력적 IP 주소 할당 버튼을 선택합니다.

![01](/docs/infra/aws/ec2/resilientip/01.png)

<br>

###  정보 입력

네트워크 경계 그룹에 실행중인 인스턴스를 선택하고 할당 버튼을 선택합니다.

![02](/docs/infra/aws/ec2/resilientip/02.png)

###  할당된 IP 확인

할당된 고정 IP를 확인할 수 있습니다. 할당된 IP를 선택해서 주소를 연결할 수 있습니다.

![03](/docs/infra/aws/ec2/resilientip/03.png)

## 2. 탄력적 IP 주소 연결

탄력적 IP 주소 연결 버튼을 선택합니다.

![04](/docs/infra/aws/ec2/resilientip/04.png)

###  정보 입력

인스턴스에 실행중인 인스턴스를 선택합니다.

프라이빗 IP 주소에는 실핼중인 인스턴스의 프라이빗 IP 주소를 입력합니다.

정보를 입력한 후에 연결 버튼을 선택 합니다.

![05](/docs/infra/aws/ec2/resilientip/05.png)

###  주소 연결 확인

할당된 IP 주소(고정 IP)에 사용중인 인스턴스 프라이빗 IP주소가
선택된 것을 확인 할 수 있습니다.

![06](/docs/infra/aws/ec2/resilientip/06.png)

이전에 사용된 IPv4 퍼블릭 IP(유동 IP)는 사용할 수 없으며
연결한 IP로 IPv4 퍼블릭 IP(고정 IP)로 변경된 것을 확인 할 수 있습니다.

## 탄력적 IP 주소 요금

[참고](https://docs.aws.amazon.com/ko_kr/AWSEC2/latest/UserGuide/elastic-ip-addresses-eip.html#eip-pricing)

탄력적 IP 주소의 효율적인 사용을 위해 탄력적 IP 주소가 실행 중인 인스턴스와 연결되어 있지 않거나 중지된 인스턴스 또는 연결되지 않은 네트워크 인터페이스와 연결된 경우 소액의 시간당 요금이 부과됩니다. 인스턴스가 실행 중인 동안에는 이와 연결된 탄력적 IP 주소 하나에 대해서는 요금이 부과되지 않지만 해당 인스턴스와 연결된 추가 탄력적 IP 주소에 대해서는 요금이 부과됩니다.






