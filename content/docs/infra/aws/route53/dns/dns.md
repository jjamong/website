---
weight: 1
slug: index
title: "DNS, 도메인"
toc: true
---

DNS(Domain Name System) 서버는 도메인과 그 도메인에 연결된 IP를 관리하는 서버다.

도메인은 도메인 네임 등록 대행자에 돈을 내고 사용하며, 산 뒤에는 원하는 IP주소를 도메인에 연결해 달라고 요청하면 도메인 네임 등록 대행자는 DNS 서버들에게 도메인과 IP 주소를 등록한다.

여러 도메인 등록 대행업체가 있고(예: AWS Route 53, 가비아, 후이즈 등등) 가격도 다르다.

## 도메인 주소의 작동방식

![01](/docs/infra/aws/route53/dns/01.png)

1. 웹 브라우저의 주소창에 test.com을 입력하고 엔터를 친다.
2. 현재 IP에서 가까운 DNS 서버에 test.com이라는 도메인의 실제 IP주소를 알고 있는지 물어본다.
3. 해당 DNS 서버가 모른다면 그 다음, 다음 DNS 서버에게 물어본다.
4. test.com의 실제 IP 주소를 알고 있는 DNS 서버를 만나면 해당 서버에서 IP 주소인 xx.xx.xx.xx를 알려준다.
5. 웹 브라우저에서 xx.xx.xx.xx라는 IP주소로 페이지를 요청한다.