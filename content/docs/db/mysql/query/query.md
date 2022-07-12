---
weight: 1
slug: index
date: 2022-02-15
title: "Query"
description: "Query 가이드"
toc: true
---

## 자식 테이블의 최근 1개 값만 가져오기

```
SELECT
	us.user_seq,
	(SELECT qna_seq FROM tb_qna qn WHERE qn.user_seq = us.user_seq ORDER BY qna_seq DESC LIMIT 1)
FROM tb_user us
```


```
SELECT
	*
FROM tb_qna qn
INNER JOIN (
	SELECT
		MAX(qna_seq) AS qun_seq
	FROM tb_qna
	GROUP BY user_seq
) qn2 ON qn2.qna_seq = us.qna_seq

```

## 자식 테이블에 일부 NULL값만 찾아서 변경

```
SELECT
	us.user_seq,
	li.like_seq
FROM tb_user us
INNER JOIN tb_like li ON us.user_seq = li.user_seq
AND li.like_count IS NULL
```

## 다중 테이블 변경

a,b 두개의 테이블에 같은 컬럼(seq)이 있고, a의 컬럼에 b컬럼을 동일하게 데이터를 업데이트 하고
싶을 때 사용된다.

```
UPDATE 테이블1 AS a, 테이블2 AS b
SET a.column = b.column,
WHERE a.seq = b.seq
```

