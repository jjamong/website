---
weight: 1
slug: index
date: 2023-11-14
title: "JWT(Json Web Token)"
description: "JWT"
toc: true
---

## 이해하기

JWT는 웹 표준을 따르고 있고, JSON 형식의 토큰에 대한 표준 규격입니다.
<br>
사용자의 인증(authentication) 또는 인가(authorization) 정보를 서버와 클라이언트 간에 안전하게 주고 받기 위해서 사용됩니다.

### JWT 구조

Header.Payload.Signature로 이루어져 있습니다.

`eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTExMTExIiwicm9sZSI6IlJPTEVfVVNFUiIsImV4cCI6MTcwNjU5NDgwMX0.5Nz4nY2kGnud4VFQ2RcsyiDSw8DIWFHVq3eF_pK_CyQ`

#### Header

Header는 일반적으로 토큰 유형과 사용중인 서명 알고리즘(HMAC, SHA256 등)이 포함됩니다.
```
// eyJhbGciOiJIUzI1NiJ9
{
    "alg": "HS256"
}
```

#### Payload

Payload는 등록된 클레임과 개인 클레임 등으로 이루어 집니다.
```
// eyJzdWIiOiJ0ZXN0MTExMTExIiwicm9sZSI6IlJPTEVfVVNFUiIsImV4cCI6MTcwNjU5NDgwMX0
{
    // 등록된 클레임
    "iss": "jjamong.github.io",
    "sub": "짜몽사이트,"
    "exp": 1706594801,
    // 개인 클레임
    "userid": "test111111",
    "role": "ROLE_USER",
}
```

#### Signature

Signature은 Header, Payload, Secret Key를 합쳐 암호화한 결과값 입니다.

```
// 5Nz4nY2kGnud4VFQ2RcsyiDSw8DIWFHVq3eF_pK_CyQ
HMACSHA256(
  base64UrlEncode(header) + "." +
  base64UrlEncode(payload),
  jXn2r5u8x/A?D*G-KaPdSgVkYp3s6v9y$B&E)H+MbQeThWmZq4t7w!z%C*F-JaNc
)
```

#### Claim
JSON 자체를 토큰으로 사용하는 것이 Claim 기반의 토큰 방식입니다.

### JWT 흐름도

#### 로그인 & 토큰발행
- 1. 유저가 아이디, 비밀번호를 입력하여 로그인
- 2. 서버에서 아이디, 비밀번호를 DB와 비교하여 맞을 경우 A(AccessToken), R(RefreshToken) 토큰을 생성함.
- 3. R 토큰을 DB 및 redis등에 저장함. A 토큰은 쿠키로 저장하거나, 프론트로 보내서 로컬스토리지쪽에 저장함.

#### 액세스 토큰 사용
- 1. 프론트에서 api 호출 시 header의 Authorization에 'Bearer A토큰' 방식처럼 전송
- 2. A토큰을 받아 복호화 한 user정보로 DB 조회 등 활용

#### 리프레쉬 토큰 사용
- 1. api 요청 시 A토큰이 만료됬는지를 검증하고 만료됬을 경우 R토큰을 읽어옴
- 2. A토큰의 유저정보와 R토큰의 유저정보가 맞을 경우 A토큰을 재생성함.
- 3. R토큰 역시 만료되어 A토큰과 R토큰 검증에 실패할 경우 로그아웃 함.