---
weight: 1
slug: index
date: 2022-04-12
title: "Cache(캐시)"
description: "Cache(캐시)는 데이터 결과를 미리 저장해두고 요청이 들어오면 원본 데이터가 아닌<br>
미리 저장해 놓은 데이터 결과를 보여주는 기법을 말합니다. 캐시를 사용해서 데이터를 저장해 놓고 이후에 데이터 요청이 있을 경우 원본 데이터를 불러오는 것보다 미리 저장해 놓은 데이터를 불러와 성능적으로 더 빠르게 결과를 출력합니다. 캐시는 결과가 바뀌지 않는 매번 동일한 결과를 출력하는 곳에 사용할 경우에 효율이 올라가며 반대로 매번 다른 결과가 출력되거나, 값이 빈번하게 수정되는 경우엔 오히려 성능적으로 떨어질 수 있습니다."
toc: true
---

## 이해하기

> Cache(캐시)는 데이터 결과를 미리 저장해두고 요청이 들어오면 원본 데이터가 아닌<br>
미리 저장해 놓은 데이터 결과를 보여주는 기법을 말합니다.

<br>

캐시를 사용해서 데이터를 저장해 놓고 이후에 데이터 요청이 있을 경우<br>
원본 데이터를 불러오는 것보다 미리 저장해 놓은 데이터를 불러와 성능적으로 더 빠르게
결과를 출력합니다.

캐시는 결과가 바뀌지 않는 매번 동일한 결과를 출력하는 곳에 사용할 경우에 효율이 올라가며

반대로 매번 다른 결과가 출력되거나, 값이 빈번하게 수정되는 경우엔 오히려<br>
성능적으로 떨어질 수 있습니다.

<br>


| Local Cache | Global Cache |
|-------------|--------------|
| 서버 내부에 캐시를 저장한다. | 여러 서버에서 캐시 서버에 접근하여 참조 할 수 있다. |
| 다른 서버의 캐시를 참조하기 어렵다. | 별도의 캐시 서버를 이용하기 때문에 서버 간 데이터 공유가 쉽다. |
| 서버 내에서 작동하기 때문에 속도가 빠르다. | 네트워크 트래픽을 사용해야 해서 로컬 캐시보다는 느리다. |
| 로컬 서버 장비의 Resource를 이용한다. (Memory, Disk) | 데이터를 분산하여 저장 할 수 있다. |

<br><br>

## Local Cache (로컬 캐시)

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

## Global Cache (글로벌 캐시) 

Multi Server 환경에서 Session, Cache 데이터를 Server 마다 나누어 관리하게 되면<br>
참조하는데 어려움이 많습니다.

그래서, 외부 서버에서 따로 관리하고 각 서버들이 참조하는 식으로 구성합니다.

### Redis (REmote DIctionary System / 레디스)

Redis는 In-Memory 기반의 키-값 형식의 비정형 데이터 구조를 가졌습니다. 

고성능 key-value 저장소로서 List, Hash, Set, Sorted Set 등 여러 형식의 자료구조를 지원하며<br>
메모리에 위치해있기 때문에 디스크보다 훨씬 빠릅니다.

이러한 이유들로 RDBMS의 캐시 및 세션 솔루션으로서 주로 사용되고 있습니다.