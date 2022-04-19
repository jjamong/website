---
weight: 1
slug: index
date: 2022-04-18
title: "Spring Mybatis(스프링 마이바티스)"
description: "Spring Mybatis 스프링 마이바티스 가이드"
toc: true
---

## 시작하기

`스프링 부트 환경`에서 설정합니다.


### 의존성 모듈 설정

```
// build.gradle

implementation 'mysql:mysql-connector-java'
implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.0'
```


### application.yml

```
// application.yml

spring:
    datasource:
        driver-class-name: com.mysql.jdbc.Driver
        jdbc-url: jdbc:mysql://localhost:3306/mysql
        username: root
        password:
```

### application.yml

```
// application.yml

spring:
    datasource:
        driver-class-name: com.mysql.jdbc.Driver
        jdbc-url: jdbc:mysql://localhost:3306/mysql
        username: root
        password:
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


### config

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

### mapper

```
// com/jjamong/mybatis/dao/IndexMapper

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.jjamong.mybatis.dao.IndexMapper">

    <select id="gets" resultType="hashMap">
        show databases 
    </select>
</mapper>
```