---
weight: 1
slug: index
date: 2022-02-26
title: "Spring Security(스프링 시큐리티)"
description: "Spring Security 스프링 시큐리티 가이드"
toc: true
---

## 시작하기

```
@RestController
@RequestMapping("/v1/test")
public class TestController {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> main() throws Exception {
        Map<String, Object> data = new HashMap<>();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> login() throws Exception {
        Map<String, Object> data = new HashMap<>();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
```

API 호출 시 결과 

![success200](/docs/back/spring/security/security/success200.png)
/docs/back/spring/security/security/

### greadle 추가

```
dependencies {
    ...
    implementation 'org.springframework.boot:spring-boot-starter-security'
    ...
}
```

security를 설치하고 톰캣 실행 후에 api를 재 호출하면 아래와 같이 오류가 발생합니다.

![fail403](/docs/back/spring/security/security/fail403.png)

### security 추가

```
// com/jjamong/backend/spring/security/WebSecurityConfig.java

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll();
    }
}
```

시큐리티 코드를 추가하고,  API를 재 호출하면 아래와 같이 다시 정상적으로 출력됩니다.

![success200](/docs/back/spring/security/security/success200.png)

## HttpSecurity


### .authorizeRequests()
요청에 대한 권한을 지정할 수 있습니다.

### denyAll()
접근을 제한합니다.
아래와 같이 전체 url(/**)에 denyAll()적용하고 호출하면 아래와 같이 접근 불가 에러가 발생합니다.

```
@Override
public void configure(HttpSecurity webSecurity) throws Exception {
    webSecurity.csrf().disable()
            .authorizeRequests()
            .antMatchers("/**").denyAll();

}
```

![fail403](/docs/back/spring/security/security/fail403.png)


### denyAll()
접근을 제한합니다.
아래와 같이 전체 url(/**)에 denyAll()적용하고 호출하면 아래와 같이 접근 불가 에러가 발생합니다.

```
// 스프링 시큐리티 룰을 무시하게 하는 Url 규칙(여기 등록하면 규칙 적용하지 않음)
@Override
public void configure(HttpSecurity webSecurity) throws Exception {
    webSecurity.csrf().disable()
            .authorizeRequests()
            .antMatchers("/**").denyAll();

}
```

![fail403](/docs/back/spring/security/security/fail403.png)


### ignore()

ignoring() 이용하여 Security FilterChain을 적용하고 싶지 않은 리소스들에 대한 설정를 할 수 있습니다.

```
@Override
public void configure(WebSecurity webSecurity) throws Exception {
    webSecurity.ignoring()
            .antMatchers("/**");
}
```

denyAll 을 전체 적용하더라도, 위와 같이 ignore를 전체 적용하고 호출하면 접근 가능합니다.
보통은 아래와 같이 외부에서 열려야 하는 이미지 등의 리소스 폴더 이하로 설정합니다.

```
// 스프링 시큐리티 룰을 무시하게 하는 Url 규칙(여기 등록하면 규칙 적용하지 않음)
@Override
public void configure(WebSecurity webSecurity) throws Exception {
    webSecurity.ignoring()
            .antMatchers("/resources/**");
}
```

![success200](/docs/back/spring/security/security/success200.png)

### anyRequest

다른 모든 요청들을 인증이나 권한 없이 허용한다는 의미입니다.

여기에 .authenticated() 메소드는 로그인된 사용자가 요청을 수행할 떄 필요합니다.
만약 사용자가 인증되지 않았다면, 스프링 시큐리티 필터는 요청을 잡아내고 사용자를 로그인 페이지로 리다이렉션 해줍니다.