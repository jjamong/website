---
weight: 1
slug: index
date: 2022-05-02
title: "JSP(Java Server Pages)"
description: "JSP(Java Server Pages)"
toc: true
---

## 시작하기

### 의존성 모듈 설정

`org.apache.tomcat.embed:tomcat-embed-jasper` : JSP 관련

`javax.servlet:jstl` : JSTL 관련


```
// build.gradle

implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
implementation 'javax.servlet:jstl'
```


### application 설정


```
// /src/main/resources/application.yml

spring:
  mvc:
    view:
      prefix: /WEB-INF/jsp/
      suffix: .jsp
```

```
// /src/main/resources/application.yml

spring.mvc.view.prefix: /WEB-INF/jsp/
spring.mvc.view.suffix: .jsp
```

### controller

```
// /src/main/java/com/jjamong/jsp/controller/IndexController.java

package com.jjamong.jsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/") 
    public String index() {
        return "index";
    }
}
```

### jsp

```
// src/main/webapp/WEB-INF/jsp/index.jsp

spring boot test
```

<br>

![jsp](/docs/back/spring/boot/jsp/jsp.png)


### JSTL

```
// src/main/webapp/WEB-INF/jsp/index.jsp

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:out value="${'jstl test'}"/>
```

<br>

![jstl](/docs/back/spring/boot/jsp/jstl.png)