---
weight: 1
slug: index
date: 2022-01-24
title: "REST API"
description: "JAVA Rest API 가이드"
toc: true
---


## REST API 라이브러리

### 1. HttpURLConnection (JAVA)

JAVA에서 기본적으로 제공하고 있는 HttpURLConnection은 HTTP통신에 필요한 핵심적인 기능을 가지고 있는 간단한 라이브러리 입니다.

테스트 코드나 가볍게 구현해 보기 좋은 장점이 있습니다.

아래와 같은 단점들로 인해서 REST API 환경을 구현하기엔 제한적 입니다.

<br>

--  응답코드가 4xx, 5xx 일 경우 다른 각 코드에 따른 예외가 아니라 IOException이 발생합니다.

--  타임아웃을 설정할 수 없습니다.

--  쿠키를 제어할 수 없습니다.


### 2. HttpClient (JAVA)

Apache에서 제공하고 있는 HttpClient은 HTTP통신에 필요한 다양한 기능을 가지고 있는 라이브러리 입니다. HttpURLConnection 라이브러리에 비교하면

<br>

--  모든 응답 코드를 확인 할 수 있습니다.

--  타임아웃, 쿠키를 사용할 수 있습니다.

<br>

위와 같은 REST API 환경을 구현하기에 기본적인 기능을 제공 하지만, 역시 반복적인 코드(http 커넥션, 오픈, 디스커넥트 등)가 많고 코드가 길어 개발에 불편함이 따릅니다.

java 11버전으로 업그레이드 되면서 java.net 패키지에 http 관련된 라이브러리가 추가 되었습니다.

(HttpURLConnection 보단 속도가 좀 느리다는 이슈가 있습니다. )


### 3. RestTemplate (Spring)

Spring에서 제공하는 있는 RestTemplate는 HTTP 통신에 필요한 기능은 물론, REST API 환경을 구현하는데 필요한 다양한 기능을 제공해줍니다.

반복적인 코드(http 커넥션, 오픈, 디스커넥트 등)를 관리해 주고 REST API 환경을 구축하는데 가장 편리한 라이브러리 입니다.

<br>

[RestTemplate](/docs/back/java/spring/resttemplate)