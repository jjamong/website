---
weight: 1
slug: index
date: 2022-06-24
title: "XSS(Cross Site Scripting)"
description: "XSS(Cross Site Scripting)"
toc: true
---

## 시작하기

### lucy-xss-servlet-filter

XSS(Cross Site Scripting) 방지를 위해 널리 쓰이는 lucy-xss-servlet-filter는 Servlet Filter 단에서<br>
`<` 등의 특수 문자를 `&lt;` 등으로 변환해주며, 여러 가지 관련 설정을 편리하게 지정할 수 있습니다.



#### index 페이지

##### Controller

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

##### jsp

```
// src/main/webapp/WEB-INF/jsp/index.jsp

<form action="/insert" method="post">
    <input type="text" name="text1" value="alert(1)" />
    <input type="text" name="text2" value="<script>alert(1)</script>" />
    <button type="submit">insert</button>
</form>
```

#### 실행

![01](/docs/back/spring/boot/webmvcconfigurer/xss/01.png)

여기서 insert 버튼을 누르면 아래와 같이 url 파라미터로 스크립트 코드가 전송되며 
<br>XSS 취약점이 발견된 것을 확인할 수 있습니다.

```
>>> text1 : alert(1)
>>> text2 : <script>alert(1)</script>
```

#### lucy 의존성 모듈 설정

```
// build.gradle

implementation 'com.navercorp.lucy:lucy-xss-servlet:2.0.0'
```

#### XSS 필터 적용

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

#### XSS 필터 룰 적용

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

#### 실행

다시 insert 버튼을 선택 하면 아래와 같이
<br>스크립트 코드가 변환되어서 전송된 것을 확인 할 수 있습니다.

```
>>> text1 : alert(1)
>>> text2 : &lt;script&gt;alert(1)&lt;/script&gt;
```

### Jackson

lucy-xss-servlet-filter는 JSON에 대한 XSS는 처리해 주지 않는다는 한계가 있습니다.

form-data 에 대해서만 적용 되고 Request Raw Body에 대해서는 처리해 주지 않습니다.<br>
그래서 JSON 형태에 값에 대해선 직접 처리를 해야 합니다.

Jackson의 com.fasterxml.jackson.core.io.CharacterEscapes를 상속하는 클래스를 직접 만들어서 처리해야 할 특수문자를 지정하고,<br>
ObjectMapper에 만든 클래스를 설정하고<br>
ObjectMapper를 MessageConverter에 등록해서 Response가 클라이언트에 나가기 전에 XSS 방지 처리 해줍니다.


#### lucy 의존성 모듈 설정

```
// build.gradle

implementation 'org.apache.commons:commons-text:1.8'
```


#### HtmlCharacterEscapes

```
package com.jjamong.xss.util;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import org.apache.commons.text.StringEscapeUtils;

public class HtmlCharacterEscapes extends CharacterEscapes {
    private final int[] asciiEscapes;

    public HtmlCharacterEscapes() {
        this.asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
        this.asciiEscapes['<'] = CharacterEscapes.ESCAPE_CUSTOM;
        this.asciiEscapes['>'] = CharacterEscapes.ESCAPE_CUSTOM;
        this.asciiEscapes['\"'] = CharacterEscapes.ESCAPE_CUSTOM;
        this.asciiEscapes['('] = CharacterEscapes.ESCAPE_CUSTOM;
        this.asciiEscapes[')'] = CharacterEscapes.ESCAPE_CUSTOM;
        this.asciiEscapes['#'] = CharacterEscapes.ESCAPE_CUSTOM;
        this.asciiEscapes['\''] = CharacterEscapes.ESCAPE_CUSTOM;
    }

    @Override
    public int[] getEscapeCodesForAscii() {
        return asciiEscapes;
    }

    @Override
    public SerializableString getEscapeSequence(int ch) {
        return new SerializedString(StringEscapeUtils.escapeHtml4(Character.toString((char) ch)));
    }

    public String unitChange(String text) {
        HtmlCharacterEscapes()
        return 
    }
}
```

#### WebMvcConfig 설정

```
package com.jjamong.xss.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jjamong.xss.util.HtmlCharacterEscapes;

@Configuration
public class WebMvcConfig {

    private final ObjectMapper objectMapper;

    @Autowired
    public WebMvcConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public MappingJackson2HttpMessageConverter jsonEscapeConverter() {
        ObjectMapper copy = objectMapper.copy();
        copy.getFactory().setCharacterEscapes(new HtmlCharacterEscapes());
        return new MappingJackson2HttpMessageConverter(copy);
    }
}

```


#### index 페이지

```
package com.jjamong.xss.controller;

import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jjamong.xss.data.Search;

@Controller
public class IndexController {

    @RequestMapping("/") 
    public ResponseEntity index(@ModelAttribute Search search) {

        HashMap<String, Object> data = new HashMap<String, Object>();

        data.put("test1", "안녕");
        data.put("test2", "<div>안녕</div>");
        data.put("test3", "<script>alert()</script>");
        data.put("test4", search.getTest());
        
        return new ResponseEntity(data, HttpStatus.OK);
    }
}
```

#### 실행

```
{"test4":"&lt;div&gt;alert()&lt;/div&gt;","test2":"&lt;div&gt;안녕&lt;/div&gt;","test3":"&lt;script&gt;alert()&lt;/script&gt;","test1":"안녕"}
```