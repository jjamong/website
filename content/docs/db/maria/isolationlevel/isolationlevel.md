---
weight: 1
slug: index
date: 2022-07-11
title: "Isolation Level(격리 수준)"
description: "Isolation Level(격리 수준)"
toc: true
---

## 이해하기


MySQL에서 전체 데이터를 scan(스캔) 하는 쿼리를 질의하게 되면 서비스에 큰 영향이 발생할 수 있습니다.


InnoDB 스토리지 엔진의 기본 Isolation Level(격리 수준)이 REPEATABLE-READ이기 때문에 발생하는 현상인데, 이것은 세션 변수 일부를 변경하여 문제를 사전에 해결할 수 있습니다.

얼마 전 이와 비슷한 장애가 발생하여 원인 분석 및 해결 방안을 포스팅합니다.