---
weight: 1
slug: index
date: 2022-06-29
title: "Mybatis 시작하기"
description: "Mybatis 시작하기"
toc: true
---




## #과 $의 차이


### #{} 방식

> 성능과 보안적으로 이점이 있어 일반적으로는 #{} 방식을 사용합니다.

<br>

#{} 방식은 PreparedStatement를 사용하는데<br>
PreparedStatement는 쿼리가 메모리에 올라가 실행되기 때문에 매번 컴파일 되지 않아(캐싱) 성능이 향상됩니다.

#{} 방식은 매개변수가 ?로 변환되며 보안적으로도 이점이 있고<br>
들어오는 데이터를 문자열로 인식하기 때문에 자동으로 따옴표가 붙습니다.'

<b>보통 일반적으로 #{} 방식을 사용합니다.</b>

#### PreparedStatement 

```
// PreparedStatement

String sql = "SELECT * FROM USER WHERE id = ?"
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setString(1, "userId");
ResultSet rst = pstmt.executeQuerey();
```

#### 사용 예

```
// 코드
SELECT * FROM USER
WHERE id = #{id}
```

```
// 결과
SELECT * FROM USER
WHERE id = ?
```

### ${} 방식

> 매개 변수에 자동 따옴표가 붙지 않아야 하는 곳에서만 사용합니다.

<br>

${} 방식은 Statement를 사용하는데,<br>
Statement는 매번 컴파일을 하기 때문에 성능적으로 떯어집니다.

${} 방식은 매개변수를 값 그대로 전달하기 떄문에 보안적(SQL Injection)으로 취약하고
문자열에 자동으로 따옴표가 붙지 않습니다.

문자열을 유동적으로 사용할 수 있어 테이블, 컬럼명, 예약어 사용에 이점이 있습니다.

<b>매개변수로 테이블, 컬럼명, 예약어 등을 사용하는 특정 상황에서만 ${} 방식을 사용합니다.</b>

### Statement

```
// Statement

String userId = "userId"
String sql = "SELECT * FROM user WHERE id = " + userId
Statement stmt = conn.credateStatement();
ResultSet rst = stmt.executeQuerey(sql);
```

#### 사용 예

```
// 코드
SELECT * FROM USER
WHERE id = "${id}"

SELECT * FROM USER
ORDER BY USER_ID ${sortOrder}
```

```
// 결과
SELECT * FROM USER
WHERE id = ?

SELECT * FROM USER
ORDER BY USER_ID DESC
```
#{sortOrder}를 사용하게된다면 ORDER BY USER_ID 'DESC' 형태로 실행이 되어 에러가 납니다.