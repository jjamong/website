---
weight: 1
slug: index
date: 2022-04-28
title: "Lombok(롬복)"
description: "자바를 이용해서 개발 할 때 각 계층간 데이터를 교환하기 위해 VO/DTO/Domain 객체를 생성하고 getter/setter/toString을 정의합니다. 객체 데이터가 추가/변경될 때 마다 매번 getter/setter도 추가/변경하게 되는 불편함이 발생됩니다. `lombok`은 어노테이션으로 설정으로 getter/setter/toString을 자동으로 만들어 주는 기능이라고 할 수 있습니다"
toc: true
---

자바를 이용해서 개발 할 때 각 계층간 데이터를 교환하기 위해 VO/DTO/Domain 객체를 생성하고 getter/setter/toString을 정의합니다.

객체 데이터가 추가/변경될 때 마다 매번 getter/setter도 추가/변경하게 되는 불편함이 발생됩니다.

`lombok`은 어노테이션으로 설정으로 getter/setter/toString을 자동으로 만들어 주는 기능이라고 할 수 있습니다


## 시작하기


### 의존성 모듈 설정

```
compileOnly 'org.projectlombok:lombok'
annotationProcessor 'org.projectlombok'
```

### VO

```
// src/main/java/com/jjamong/lombok/vo/User.java

package com.jjamong.lombok.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
 
@Getter @Setter
@ToString
public class User {
 
    private String id;
    private String name;
    private String pass;
 
}
```

### Controller

```
// src/main/java/com/jjamong/lombok/controller/IndexController.java

package com.jjamong.lombok.controller;

import java.util.HashMap;

import com.jjamong.lombok.vo.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/") 
    public ResponseEntity index() {

        HashMap<String, Object> data = new HashMap<String, Object>();

        User user = new User();
        user.setId("testId");
        data.put("user", user.getId());
        
        return new ResponseEntity(data, HttpStatus.OK);
    }
}
```

### 결과

```
{"user":"testId"}
```

## eclipse(이클립스) 설정

`Lombok 설치` [https://projectlombok.org/download](https://projectlombok.org/download)

### java.lang 오류 발생

eclipse.ini 파일을 열어서 아래 라인을 주석처리 합니다.

```
#plugins/org.eclipse.justj.openjdk.hotspot.jre.full.win32.x86_64_17.0.3.v20220515-1416/jre/bin
```