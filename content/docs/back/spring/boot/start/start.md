---
weight: 1
slug: index
date: 2021-05-29
title: "Spring Boot 시작하기"
description: "Spring Boot 시작하기"
toc: true
---

## 시작하기

### VScode 환경 & VScode 플러그인 설치

- -[Java Extension Pack](/docs/etc/etc/vscode/#java)
- -[Spring Boot](/docs/etc/etc/vscode/#spring-boot)
- -[JAVA 환경 변수 설정](/docs/infra/os/window/#java)

### 1. 프로젝트 만들기

보기(View) > 명령 팔레트(Command Pallette)를 선택합니다. (`Ctrl + Shift + P`)

`Spring Initializr`를 검색하고 스프링 프로젝트를 선택합니다.

![gradle_select](/docs/back/spring/boot/start/gradle_select.png)

이어서 아래와 같이합니다.

```
Specify Spring Boot version. : 스프링 버전 선택
Specify project language. : 언어 선택 (java)
Input Group Id for your project. : 프로젝트 그룹 (com.example)
Input Artifact Id for your project. : 프로젝트 이름(springboottest)
Specify packaging type. : Jar
Specify Java Version. : 자바 버전 (8)
Search for dependencies. : (검색 후 선택)
 - Spring Boot DevTools
 - Spring Web
```
dependencies 까지 선택 후 프로젝트 폴더를 선택해서 프로젝트를 생성합니다.


### 2. 실행

`F5` 및 `실행 > 디버깅 시작` 

![gradle_select](/docs/back/spring/boot/start/start.png)


### ResponseEntity (API)

파일을 추가하고 실행하면 아래와 같이 출력됩니다.

```
package com.jjamong.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/") 
    public ResponseEntity index() {

        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

        return new ResponseEntity(list, HttpStatus.OK);
    }
}
```
![responseEntity](/docs/back/spring/boot/start/responseEntity.png)



### JSP

application.yml 파일을 추가 하고 jsp 설정을 추가합니다.
```
// /src/main/resources/application.yml

spring:
  mvc:
    view:
      prefix: /WEB-INF/jsp/
      suffix: .jsp
```

build.gradle 파일에 서버를 설정합니다.
```
// build.gradle

dependencies {
    ...
    // tomcat-embed-jasper 추가
	implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
}

```

controller와 jsp를 추가합니다.
```
// /src/main/com/example/springboottest/IndexController.java

package com.example.springboottest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/") 
    public String index() {
        return "test";
    }
}
```

```
// /src/webapp/WEB-INF/jsp/test.jsp

spring boot test
```
![jsp](/docs/back/spring/boot/start/jsp.png)