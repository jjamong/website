---
weight: 1
slug: index
date: 2022-04-12
title: "Spring Cache(스프링 캐시)"
description: "Spring Cache(스프링 캐시)"
toc: true
---

## 시작하기

`스프링 부트 환경`에서 설정합니다.

### 의존성 모듈 설정

```
// build.gradle

implementation 'org.springframework.boot:spring-boot-starter-cache'
```

### @EnableCacheing 어노테이션 설정

`@EnableCacheing`  동작에 필요한 기본적인 설정들을 등록합니다.

```
// /src/main/java/com/jjamong/cache/CacheApplication.java

package com.jjamong.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheApplication.class, args);
	}

}

```

### DB 설정 및 셋팅

[mybatis 설정](http://localhost:1313/docs/back/spring/boot/mybatis/) 에서 mybatis에 select까지 해서 api로 출력하는 것까지 참고한 후에, 

컨트롤, 서비스에 각 로그를 아래와 같이 나오도록 추가합니다.

```
>>> IndexController
>>> IndexService
2022-04-26 14:55:19.505  INFO 9468 --- [nio-8080-exec-1] com.zaxxer.hikari.HikariDataSource       : HikariPool-12 - Starting...
2022-04-26 14:55:19.506  WARN 9468 --- [nio-8080-exec-1] com.zaxxer.hikari.util.DriverDataSource  : Registered driver with driverClassName=com.mysql.jdbc.Driver was not found, trying direct instantiation.
2022-04-26 14:55:19.513  INFO 9468 --- [nio-8080-exec-1] com.zaxxer.hikari.HikariDataSource       : HikariPool-12 - Start completed.
>>> IndexService res[{Database=information_schema}, {Database=mysql}, {Database=performance_schema}, {Database=phpmyadmin}, {Database=test}]
>>> IndexController list[{Database=information_schema}, {Database=mysql}, {Database=performance_schema}, {Database=phpmyadmin}, {Database=test}]
```

### @Cacheable 어노테이션 추가

아래와 같이 어노테이션 추가 후 api를 실행하면 로그가 올라오지 않는 것을 확인 할 수 있습니다.
```
@Cacheable("/")
@GetMapping("/")
public ResponseEntity index() {
	System.out.println(">>> IndexController");

	List<HashMap<String, Object>> list = indexService.gets();
	
	System.out.println(">>> IndexController list" + list);

	return new ResponseEntity(list, HttpStatus.OK);
}
```

- - -

### @Cacheable

@Cacheable은 스프링에서 제공하는 캐시관련 어노테이션으로 aop방식으로 동작합니다.
Key값의 지정에는 SpEL이 사용됩니다.

```
@Cacheable(key = "index" , value = "jjamong_cache")
public ResponseEntity index() {return new ResponseEntity(HttpStatus.OK);}

@Cacheable(key = "T(com.jjamong.cacheutil).createKey(#root.targetClass, #root.methodName, #root.args)" , value = "jjamong_cache")
public ResponseEntity index() {return new ResponseEntity(HttpStatus.OK);}
```