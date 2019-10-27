---
title: 아키텍처 이해
section_main: true
docs_home: true
layout: docs_home
weight: 10
---

# 아키텍처의 이해
서비스에 따른 애플리케이션, DB 서버 구성. 로드밸런서 구조를 설명

## [단일서버](/docs/blog/architecture/single)

![단일서버](/docs/blog/architecture/single/01.png)

가장 기본적인 서버 구성으로 테스트 서버나, 간단한고 작은 서비스를 할 때 많이 사용된다.

## [애플리케이션/데이터베이스 서버 분리](/docs/blog/architecture/separation)

![애플리케이션/데이터베이스 서버 분리](/docs/blog/architecture/separation/01.png)

단일 서버 구성에서 데이터베이스를 별도의 서버로 분리한 구성.

단일서버의 단점인 전체 서비스 장애 확률, 효율적인 자원 사용, 보안취약이
해결된다.

## [로드밸런서](/docs/blog/architecture/loadbalancer)

![로드밸런서](/docs/blog/architecture/loadbalancer/01.png)
클라이언트는 로드 밸런서 서버와 통신하고 그 뒤에 여러 애플리케이션 서버를 두는 구성.