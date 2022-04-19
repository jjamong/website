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
