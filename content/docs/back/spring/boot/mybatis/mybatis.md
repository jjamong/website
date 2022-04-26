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

`mysql:mysql-connector-java` mysql 커넥션 모듈

`mybatis-spring-boot-starter` mybatis 모듈


```
// build.gradle

implementation 'mysql:mysql-connector-java'
implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.0'
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