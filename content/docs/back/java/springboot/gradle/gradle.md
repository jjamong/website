---
weight: 1
slug: index
date: 2021-11-29
title: "Gradle 설정"
description: "자바 스프링 부트(JAVA Spring Boot) Gradle 설정"
toc: true
---

https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/

https://willbesoon.tistory.com/93


## 저장소

mavenCentral은 Maven Repository Server에서 라이브러리를 가져옵니다.

```
repositories {
    mavenCentral()
}

```

mavenCentral은 Maven Repository Server에서 라이브러리를 가져옵니다.

```
repositories {
    maven { url '다른 저장소 url'}
}
```

