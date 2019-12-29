---
weight: 2
slug: index
title: "웹서버"
---

# 웹서버

## Apache

`url` https://www.apachelounge.com/download/

- window 계열은 위 주소에서 다운
- 리눅스 계열은 yum 설치

### Apache 패키지 목록 명령어
```
$ yum list | grep httpd
```
httpd가 이름에 포함된 패키지 목록 확인.

![01](/docs/infra/aws/ec2/webserver/01.png)

Apache 2.2 패키지 명은 httpd 이며, 2.4 패키지 명은 httpd24이다.

### Apache 설치 명령어
```
$ sudo yum install httpd24
```

### Apache 시작/종료/재시작

```
// Apache 시작
$ sudo service httpd start

// Apache 종료
$ sudo service httpd stop

// Apache 재시작
$ sudo service httpd restart
```

### Apache 접속

![02](/docs/infra/aws/ec2/webserver/02.png)


서버를 실행했는데도 아래와 같이 접속되지 않는다면,
[EC2 HTTP 보안그룹](/docs/infra/aws/ec2/securitygroup#http-https)을 확인해 보자.
![03](/docs/infra/aws/ec2/webserver/03.png)

