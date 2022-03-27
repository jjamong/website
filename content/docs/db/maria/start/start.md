---
weight: 1
slug: index
date: 2022-01-06
title: "MARIA"
description: "MARIA 가이드"
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

## 업데이트


### 다중 테이블

a,b 두개의 테이블에 같은 컬럼(seq)이 있고, a의 컬럼에 b컬럼을 동일하게 데이터를 업데이트 하고
싶을 때 사용된다.

```
UPDATE 테이블1 AS a, 테이블2 AS b
SET a.column = b.column,
WHERE a.seq = b.seq
```

