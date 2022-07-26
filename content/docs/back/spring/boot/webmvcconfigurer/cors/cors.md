---
weight: 1
slug: index
date: 2022-07-13
title: "CORS(Cross-Origin Resource Sharing)"
description: "CORS(Cross-Origin Resource Sharing)"
toc: true
---

## CORS 오류 발생

```
// src/main/java/com/jjamong/cors/controller/IndexController.java

@RequestMapping(value = "/", method = RequestMethod.GET)
public ResponseEntity index() {

    HashMap<String, Object> data = new HashMap<String, Object>();
    
    return new ResponseEntity(data, HttpStatus.OK);
}
```

```
// index.html

<script src="./jquery-3.3.1.min.js"></script>
<script>
    $.ajax({     
        method: "GET",
        url: "http://localhost:8080",    
    })
    .done(function(json) {
        console.log(json)
    })

</script>
```

index.html 파일을 열고 network를 확인해 보면 아래와 같이 CORS 오류가 발생합니다.

![01](/docs/back/spring/boot/webmvcconfigurer/cors/01.png)


## CORS 허용 설정

````
package com.jjamong.cors.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "OPTIONS")
                .allowedHeaders("headers")
                .maxAge(3000);
    }
}
````

모든 요청에 대해서 허용하는 설정을 하고 index.html파일을 새로고침하면<br>
아래와 같이 CORS 오류가 해결된 것을 확인할 수 있습니다.

![02](/docs/back/spring/boot/webmvcconfigurer/cors/02.png)