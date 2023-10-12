---
weight: 1
slug: index
date: 2022-04-21
title: "JPA(Java Persistence API)"
description: "JPA(Java Persistence API) 가이드"
toc: true
---

## 시작하기

### 의존성 모듈 설정

`spring-boot-starter-data-jpa` JPA 모듈<br>
`mysql:mysql-connector-java` mysql 커넥션 모듈

```
// build.gradle

implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
implementation 'mysql:mysql-connector-java'
```

### application.yml

```
// src/main/resources/application.yml

spring:
    datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/mysql
        username: root
        password:
```
