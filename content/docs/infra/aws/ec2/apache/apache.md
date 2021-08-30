---
weight: 1
slug: index
date: 2021-08-30
title: "아파치 Apache"
description: "AWS Apache 가이드"
toc: true
---

## 시작하기

`url` https://www.apachelounge.com/download/

### 패키지 목록 명령어
```
$ yum list | grep httpd
```
httpd가 이름에 포함된 패키지 목록 확인.

![01](/docs/infra/aws/ec2/apache/01.png)

### 설치 명령어
```
$ sudo yum install httpd
```

설치가 완료되면 버전을 확인할 수 있다.
```
$ httpd -v
Server version: Apache/2.4.48 ()
Server built:   Jun 25 2021 18:53:37
```

### 시작/종료/재시작

```
// Apache 시작
$ sudo service httpd start

// Apache 종료
$ sudo service httpd stop

// Apache 재시작
$ sudo service httpd restart
```

### WEB 접속

![02](/docs/infra/aws/ec2/apache/02.png)

서버를 실행했는데도 아래와 같이 접속되지 않는다면,
[EC2 HTTP 보안그룹](/docs/infra/aws/ec2/securitygroup#http-https)을 확인해 보자.
![03](/docs/infra/aws/ec2/apache/03.png)

### index.html 확인

설정 정보 경로 : `/etc/httpd/conf`

웹 루트 경로 : `/var/www/html`

설치를 root 계정으로 했으므로, 권한을 변경한다.

```
// 실행

$ cd /var/www/
$ sudo chown ec2-user:ec2-user ./html
$ ls -la
```

```
// 결과

total 0
drwxr-xr-x  4 root     root      33 Aug 30 02:49 .
drwxr-xr-x 20 root     root     280 Aug 30 02:49 ..
drwxr-xr-x  2 root     root       6 Jun 25 18:54 cgi-bin
drwxr-xr-x  2 ec2-user ec2-user  24 Aug 30 03:00 html
```

html 폴더 권한을 변경 했으면,
vi 에디터로 html폴더에 index.html을 만들고 접속한다.

![04](/docs/infra/aws/ec2/apache/04.png)