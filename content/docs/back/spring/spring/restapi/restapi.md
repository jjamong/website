---
weight: 1
slug: index
date: 2022-06-29
title: "REST API"
description: "REST API"
toc: true
---

### @RequestMapping

```
@RequestMapping(value = "/user", method = RequestMethod.GET)
public ResponseEntity index() {

    HashMap<String, Object> data = new HashMap<String, Object>();
    
    return new ResponseEntity(data, HttpStatus.OK);
}
```

#### value
URL 정보를 입력합니다.

#### method
HTTP Method(GET, POST, PUT, DELETE)로 표현합니다.



### ResponseEntity

> Spring 에서 제공하는 ResponseEntity는 HttpEntity를 상속(확장)하여 header, body, httpStatus
<br>까지 모두 제어할 수 있는 클래스입니다.

<br>

Spring 에서 제공하는 클래스 중에는 HttpEntity 클래스가 있는데 이 클래스는<br>
HTTP 요청/응답의 Header와 Body를 포함하고 있습니다.

ResponseEntity 클래스는 이 HttpEntity 클래스를 상속받아서<br>
HTTP 요청/응답에 따른 Hedaer(HTTP 상태 코드 등)와 Body(json 결과 값 등)를 제어하고 개발할 수 있습니다.

에러 코드와 같은 HTTP 상태 코드와 데이터를 같이 제어 할 수 있기 때문에 전송할 수 있기 때문에 좀 더 세밀한 제어가 필요한 경우 사용한다고 합니다.

