---
weight: 1
slug: index
title: "인스턴스 생성/접속"
toc: true
---

`위치` : 인스턴스 > 인스턴스

인스턴스 목록을 확인할 수 있다.<br>인스턴스 시작 버튼을 선택하여 인스턴스를 생성한다.

![EC2](/docs/infra/ec2/instancecreate/02.png)

--- 

## 1. 단계 1: AMI 선택

AMI(Amazon Machine Image)는 원하는 EC2 환경(OS, 애플리케이션 등)을 구성한 이미지로
EC2에 미리 셋팅한 AMI를 설정하는 구조다.

![03](/docs/infra/ec2/instancecreate/03.png)


## 2. 단계 2: 인스턴스 유형 선택

EC2 서버의 CPU, 메모리, 디스크 등 스펙을 설정한다.

![04](/docs/infra/ec2/instancecreate/04.png)

## 3. 단계 3: 인스턴스 세부 정보 구성

인스턴스 상세 정보 구성.
(기본 값으로 두고 다음 진행)

![05](/docs/infra/ec2/instancecreate/05.png)

## 4. 단계 4: 스토리지 추가

인스턴스 스토리지 추가.
(기본 값으로 두고 다음 진행)

![06](/docs/infra/ec2/instancecreate/06.png)

## 5. 단계 5: 태그 추가

인스턴스를 분류할 때 유용하게 사용 가능
(기본 값으로 두고 다음 진행)

![07](/docs/infra/ec2/instancecreate/07.png)

## 6. 단계 6: 보안 그룹 구성

보안을 위해 IP, Port를 이용해 정의해 두는 접속 규칙

인스턴스 접근 제어를 위한 보안 그룹 구성.
생성하거나, 기존에 만들어 둔 보안 그룹이 있다면 사용 가능

![08](/docs/infra/ec2/instancecreate/08.png)


## 7. 단계 7: 인스턴스 시작 검토

인스턴스 생성 전에 설정한 정보들을 확인한다.

![09](/docs/infra/ec2/instancecreate/09.png)

## 8. 기존 키페어 선택 또는 새 키 페어 생성

서버에 접속하기 위한 공개 키 암호화 기법으로 서버에는 공개키(public key) 사용자는 개인 키(private key)를 가지고 접속함.

키페어를 생성하거나, 기존에 만들어 둔 키를 설정한다.

![10](/docs/infra/ec2/instancecreate/10.png)

## 9. 생성된 인스턴스 확인

인스턴스 생성 전에 설정한 정보들을 확인한다.
퍼블릭 DNS와 IP가 호스트이며, 따로 설정하지 않았을 경우엔 유동IP로 설정되므로 서버 재부팅시 IP가 변경됨.

![11](/docs/infra/ec2/instancecreate/11.png)

# 인스턴스 접속

## SSH 접속 설정
- `프로토콜` SSH
- `호스트` 퍼블릭 DNS, IP
- `방법` Publick Key (공개 키 암호화 기법)
- `사용자 이름` ec2-usercentOS계열은 ec2-user, ubuntu계열은 ubuntu 입니다.)
- `사용자 키` 인스턴스 생성시에 설정했던 pem키를 설정
![12](/docs/infra/ec2/instancecreate/12.png)
![13](/docs/infra/ec2/instancecreate/13.png)

## EC2 서버 접속 화면
![14](/docs/infra/ec2/instancecreate/14.png)