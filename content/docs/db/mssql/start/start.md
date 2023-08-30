---
weight: 1
slug: index
date: 2023-08-09
title: "MSSQL"
description: "MSSQL"
toc: true
---

## 시작하기

### TOP
데이터 조회 시 특정 개수만 가져올때 사용합니니다.<br>

-- MYSQL의 limit과 동일한 기능입니다.

```
SELECT TOP 10 * FROM table
```

### JOIN

```
// ANSI
SELECT Test1.*, Test2.name
FROM Test1 LEFT OUTER JOIN Test2
ON Test1.num = Test2.num

SELECT Test1.*, Test2.name
FROM Test1 RIGHT OUTER JOIN Test2
ON Test1.num = Test2.num

// MSSQL
SELECT Test1.*, Test2.name
FROM Test1 , Test2
WHERE Test1.num *= Test2.num

SELECT Test1.*, Test2.name
FROM Test1 , Test2
WHERE Test1.num =* Test2.num
```