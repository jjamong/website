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
