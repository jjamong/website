---
weight: 1
slug: index
date: 2022-04-21
title: "mybatis(마이바티스)"
description: "mybatis 스프링 마이바티스 가이드"
toc: true
---

## 시작하기

### 의존성 모듈 설정

`mybatis-spring-boot-starter` mybatis 모듈<br>
`mysql:mysql-connector-java` mysql 커넥션 모듈

```
// build.gradle

implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.0'
implementation 'mysql:mysql-connector-java'
```

### application.yml

```
// src/main/resources/application.yml

spring:
    datasource:
        driver-class-name: com.mysql.jdbc.Driver
        jdbc-url: jdbc:mysql://localhost:3306/mysql
        username: root
        password:
```

### mybatis config

`classpath:/com/jjamong/mybatis/dao/*.xml` mapper 위치

```
package com.jjamong.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.jjamong.mybatis.dao", sqlSessionFactoryRef = "SqlSessionFactory")
public class MybatisConfig {

    @Value("classpath:/com/jjamong/mybatis/dao/*.xml")
    String mPath;

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource DataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory SqlSessionFactory(@Qualifier("dataSource") DataSource DataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean =  new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(DataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(mPath));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "SessionTemplate")
    public SqlSessionTemplate SqlSessionTemplate(@Qualifier("SqlSessionFactory") SqlSessionFactory firstSqlSessionFactory) {
        return new SqlSessionTemplate(firstSqlSessionFactory);
    }

}
```

### controller

```
package com.jjamong.mybatis.controller;

import java.util.HashMap;
import java.util.List;

import com.jjamong.mybatis.service.IndexService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private IndexService indexService;

    @GetMapping("/")
    public ResponseEntity AllUsers() {

        List<HashMap<String, Object>> list = indexService.gets();

        return new ResponseEntity(list, HttpStatus.OK);
    }
}
```

### service

```
package com.jjamong.mybatis.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jjamong.mybatis.dao.IndexMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexService {
    
    @Autowired
    private IndexMapper indexMapper;

    public List<HashMap<String, Object>> gets() {

        List<HashMap<String, Object>> res = new ArrayList<HashMap<String, Object>>();

        res = indexMapper.gets();

        return res;
    } 
}
```

### dao

```
package com.jjamong.mybatis.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IndexMapper {
    
    public List<HashMap<String, Object>> gets();
}
```

### mapper

```
// resources/com/jjamong/mybatis/dao/IndexMapper

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.jjamong.mybatis.dao.IndexMapper">

    <select id="gets" resultType="hashMap">
        show databases 
    </select>
</mapper>
```



## 이해하기

### #과 $의 차이

#### #{} 방식

> 성능과 보안적으로 이점이 있어 일반적으로는 #{} 방식을 사용합니다.

<br>

#{} 방식은 PreparedStatement를 사용하는데<br>
PreparedStatement는 쿼리가 메모리에 올라가 실행되기 때문에 매번 컴파일 되지 않아(캐싱) 성능이 향상됩니다.

#{} 방식은 매개변수가 ?로 변환되며 보안적으로도 이점이 있고<br>
들어오는 데이터를 문자열로 인식하기 때문에 자동으로 따옴표가 붙습니다.'

<b>보통 일반적으로 #{} 방식을 사용합니다.</b>

##### PreparedStatement 

```
// PreparedStatement

String sql = "SELECT * FROM USER WHERE id = ?"
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setString(1, "userId");
ResultSet rst = pstmt.executeQuerey();
```

##### 사용 예

```
// 코드
SELECT * FROM USER
WHERE id = #{id}
```

```
// 결과
SELECT * FROM USER
WHERE id = ?
```

#### ${} 방식

> 매개 변수에 자동 따옴표가 붙지 않아야 하는 곳에서만 사용합니다.

<br>

${} 방식은 Statement를 사용하는데,<br>
Statement는 매번 컴파일을 하기 때문에 성능적으로 떯어집니다.

${} 방식은 매개변수를 값 그대로 전달하기 떄문에 보안적(SQL Injection)으로 취약하고
문자열에 자동으로 따옴표가 붙지 않습니다.

문자열을 유동적으로 사용할 수 있어 테이블, 컬럼명, 예약어 사용에 이점이 있습니다.

<b>매개변수로 테이블, 컬럼명, 예약어 등을 사용하는 특정 상황에서만 ${} 방식을 사용합니다.</b>

#### Statement

```
// Statement

String userId = "userId"
String sql = "SELECT * FROM user WHERE id = " + userId
Statement stmt = conn.credateStatement();
ResultSet rst = stmt.executeQuerey(sql);
```

##### 사용 예

```
// 코드
SELECT * FROM USER
WHERE id = "${id}"

SELECT * FROM USER
ORDER BY USER_ID ${sortOrder}
```

```
// 결과
SELECT * FROM USER
WHERE id = ?

SELECT * FROM USER
ORDER BY USER_ID DESC
```
#{sortOrder}를 사용하게된다면 ORDER BY USER_ID 'DESC' 형태로 실행이 되어 에러가 납니다.