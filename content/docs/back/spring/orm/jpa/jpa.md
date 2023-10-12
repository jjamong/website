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


### controller

```
package com.jjamong.hormone.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jjamong.hormone.common.response.ResponseCommon;
import com.jjamong.hormone.user.request.RequestUser;
import com.jjamong.hormone.user.response.ResponseUser;
import com.jjamong.hormone.user.service.JoinService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
public class UserController {

        private final JoinService joinService;

        @RequestMapping(value = "/checkId", method = { RequestMethod.POST })
        public ResponseEntity<ResponseCommon<Object>> checkId(@RequestBody RequestUser user) {

                ResponseUser responseUser = joinService.checkUserId(user.getUserId());

                return new ResponseEntity(responseUser, HttpStatus.OK);
        }

}
```

### service

```
package com.jjamong.hormone.user.service;

import com.jjamong.hormone.user.response.ResponseUser;

public interface JoinService {

   ResponseUser checkUserId(String userId);

}
```

### serviceImpl

```
package com.jjamong.hormone.user.serviceImpl;

import org.springframework.stereotype.Service;

import com.jjamong.hormone.user.repository.JoinRepository;
import com.jjamong.hormone.user.response.ResponseUser;
import com.jjamong.hormone.user.service.JoinService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService {

   final JoinRepository loginRepository;

   @Override
   public ResponseUser checkUserId(String userId) {
      return loginRepository.findByUserId(userId);
   }

}
```

### repository

```
package com.jjamong.hormone.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jjamong.hormone.user.entity.UserEntity;
import com.jjamong.hormone.user.response.ResponseUser;

@Repository
public interface JoinRepository extends JpaRepository<UserEntity, Long> {
   ResponseUser findByUserId(String userId);

}
```

### entity
```
package com.jjamong.hormone.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Builder
@Data
@Table(name = "user")
@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;

    @NonNull
    @Column(unique = true)
    private String userId;

}
```

