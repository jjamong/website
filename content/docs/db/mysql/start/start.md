---
weight: 1
slug: index
date: 2022-01-06
title: "MYSQL"
description: "MYSQL"
toc: true
---

## 프로세스

DB의 프로세스를 관리하는 기능이다.

DB의 쿼리가 실행되지 않고 timeout이 발생하는 경우에 DB 프로세스를 통해
현재 db쿼리가 실행되고 있는 상태를 확인 할 수 있다.

### 프로세스 확인

```
show processlist;
select * from information_schema.processlist;
```


### 프로세스 킬

```
kill 'id번호';
```

## 임시 테이블

### WITH
	```
	WITH TBL AS
	(
		SELECT '철수' AS NAME, 20 AS AGE
		UNION ALL
		SELECT NAME, AGE
		FROM TB1
	)

	SELECT NAME, AGE FROM TBL;
	```

### Temporary Table

MYSQL은 temporary table 을 session 이 끝나거나 connection 이 종료되었을 때 자동으로 삭제합니다.<br>
접속한 세션 안에서만 생성하고 사용할  수 있다는 의미입니다.

temporary table 은 오직 접근 가능한 client 만 접근할 수 있습니다. 다른 client들은 오직 특정 client 만 table 을 볼 수 있기 때문에 특별한 오류 없이 같은 이름의 temporary table을 생성할 수 있습니다.<br>
같은 session 에 두개의 temporary tables 는 같은 이름의 table 이 공유될 수 없습니다.

#### CREATE TEMPORARY TABLE
```
CREATE TEMPORARY TABLE table_name( 
	SEQ INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY
	,SEQ1 INT
	,SEQ2 INT
);
```


#### DROP TEMPORARY TABLE
```
DROP TEMPORARY TABLE TABLE
```