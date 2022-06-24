---
weight: 1
slug: index
date: 2022-05-03
title: "XSS(Cross Site Scripting)"
description: "XSS(Cross Site Scripting)"
toc: true
---

## 시작하기

### index 페이지

#### Controller

```
// src/main/java/com/jjamong/xss/controller/IndexController.java


package com.jjamong.xss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @RequestMapping("/") 
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST) 
    public String insert(@RequestParam("text1") String text1, @RequestParam("text2") String text2) {
        System.out.println(">>> text1 : " + text1);
        System.out.println(">>> text2 : " + text2);
        
        return "redirect:/";
    }
}
```

#### jsp

```
// src/main/webapp/WEB-INF/jsp/index.jsp

<form action="/insert" method="post">
    <input type="text" name="text1" value="alert(1)" />
    <input type="text" name="text2" value="<script>alert(1)</script>" />
    <button type="submit">insert</button>
</form>
```

### 실행

![01](/docs/back/spring/boot/xss/01.png)

여기서 insert 버튼을 누르면 아래와 같이 url 파라미터로 스크립트 코드가 전송되며 
<br>XSS 취약점이 발견된 것을 확인할 수 있습니다.

```
>>> text1 : alert(1)
>>> text2 : <script>alert(1)</script>
```

### 의존성 모듈 설정

```
// build.gradle

implementation 'com.navercorp.lucy:lucy-xss-servlet:2.0.0'
```


### XSS 필터 적용

```
// src/main/java/com/jjamong/xss/config/WebMvcConfig.java

package com.jjamong.xss.config;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebMvcConfig extends WebMvcAutoConfiguration {
    
    //Lucy Xss filter 적용	
    @Bean
    public FilterRegistrationBean<XssEscapeServletFilter> getFilterRegistrationBean(){
        FilterRegistrationBean<XssEscapeServletFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new XssEscapeServletFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
```

### XSS 필터 룰 적용

```
// src/main/resources/lucy-xss-servlet-filter-rule.xml

<?xml version="1.0" encoding="UTF-8"?>
<config xmlns="http://www.navercorp.com/lucy-xss-servlet">
   <defenders>
       <!-- XssPreventer 등록 -->
       <defender>
           <name>xssPreventerDefender</name>
           <class>com.navercorp.lucy.security.xss.servletfilter.defender.XssPreventerDefender</class>
       </defender>

   </defenders>

    <!-- default defender 선언, 별다른 defender 선언이 없으면 default defender를 사용해 필터링 한다. -->
    <default>
        <defender>xssPreventerDefender</defender>
    </default>
</config>
```

### 실행

다시 insert 버튼을 선택 하면 아래와 같이
<br>스크립트 코드가 변환되어서 전송된 것을 확인 할 수 있습니다.

```
>>> text1 : alert(1)
>>> text2 : &lt;script&gt;alert(1)&lt;/script&gt;
```