---
weight: 1
slug: index
date: 2022-04-29
title: "swagger(스웨거)"
description: "`Swagger` 는 OAS(Open Api Specification) 입니다. API 문서화를 웹 UI로 편리하게 제공하며, 파라미터와 header 등을 변경해 가면서 API 테스트도 가능합니다."
toc: true
---

`Swagger` 는 OAS(Open Api Specification) 입니다.

API 문서화를 웹 UI로 편리하게 제공하며, 파라미터와 header 등을 변경해 가면서 API 테스트도 가능합니다.


## 시작하기


### 의존성 모듈 설정

```
// build.gradle

implementation 'io.springfox:springfox-boot-starter:3.0.0'
implementation 'io.springfox:springfox-swagger-ui:3.0.0'
```

의존성 모듈을 설치한 후에 서버를 실했시켰을 때 아래와 같은 에러가 발생하면

`application.yml` 파일에 코드를 추가해 줍니다.

```
2022-04-29 14:21:13.114  INFO 9624 --- [  restartedMain] ConditionEvaluationReportLoggingListener : Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
2022-04-29 14:21:13.123 ERROR 9624 --- [  restartedMain] o.s.boot.SpringApplication               : Application run failed   
org.springframework.context.ApplicationContextException: Failed to start bean 'documentationPluginsBootstrapper'; nested exception is java.lang.NullPointerException: Cannot invoke "org.springframework.web.servlet.mvc.condition.PatternsRequestCondition.getPatterns()" because "this.condition" is null
```

```
// application.yml

spring:
    mvc:
        pathmatch:
            matching-strategy: ANT_PATH_MATCHER
```


### swagger config

```
// src/main/java/com/jjamong/swagger/config/SwaggerConfig.java

package com.jjamong.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
```

### controller

```
// src/main/java/com/jjamong/swagger/controller/IndexController.java

package com.jjamong.swagger.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public ResponseEntity index() {

        HashMap<String, Object> data = new HashMap<String, Object>();
        
        return new ResponseEntity(data, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity test() {

        HashMap<String, Object> data = new HashMap<String, Object>();
        
        return new ResponseEntity(data, HttpStatus.OK);
    }
}
```

### 결과

swagger 3.x : http://localhost:8080/swagger-ui/index.html

![swagger](/docs/back/spring/boot/swagger/01.png)