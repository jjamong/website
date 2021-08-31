---
weight: 1
slug: index
date: 2021-08-31
title: "Maria"
description: "Maria"
toc: true
---

## 시작하기

### 설치

```
$ sudo amazon-linux-extras install -y lamp-mariadb10.2-php7.2
```

### 시작/종료/재시작

```
// mariadb 시작
$ sudo systemctl start mariadb
```

### root 계정 설정

```
mysqladmin -u root -p password '1234'
Enter password: 그냥 엔터

mysql -u root -p
Enter password:1234
```

### plantdiary 계정&DB 추가

```
create user 'plantdiary'@'%' identified by '1234';
create user 'plantdiary'@'localhost' identified by '1234';

create database plantdiary default character set utf8;

GRANT ALL privileges ON plantdiary.* TO plantdiary@'%' IDENTIFIED BY '1234';
GRANT ALL privileges ON plantdiary.* TO plantdiary@locahost IDENTIFIED BY '1234';

SHOW GRANTS FOR plantdiary@'%';
SHOW GRANTS FOR plantdiary@localhost;

flush privileges;
```