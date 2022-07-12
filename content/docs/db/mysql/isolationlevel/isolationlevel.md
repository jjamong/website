---
weight: 1
slug: index
date: 2022-07-11
title: "Isolation Level(격리 수준)"
description: "Isolation Level(격리 수준)"
toc: true
---

## 이해하기

> 여러 트랜젝션이 처리될 때 특정 트랜젝션이 다른 트랜젝션에서 조회, 변경 하는 수준을 설정하는 것을 말합니다.

<br>

MySQL에서 전체 데이터를 scan(스캔) 하는 쿼리를 질의하게 되면 서비스에 큰 영향이 발생할 수 있습니다.

InnoDB 스토리지 엔진의 Isolation Level(격리 수준)이 기본으로 `REPEATABLE-READ` 이기 때문에 발생하는데,<br>
세션 변수 일부를 변경하여 문제를 해결할 수 있습니다.

### 격리 수준

#### READ UNCOMMITTED

트랜잭션에서 변경하는 내용이 commit과 rollback여부에 관계 없이 다른 트랜잭션에게 노출됩니다. 

발생 가능한 문제점 - DIRTY READ, NON REPEATABLE READ, PHANTOM READ

#### READ COMMITTED

트랜잭션에서 변경한 레코드는 commit이 완료된 데이터만 조회할 수 있게 만듭니다. commit 전에 조회를 시도한다면 UNDO영역에 백업된 레코드를 조회할 수 있습니다.

발생 가능한 문제점 - NON REPEATABLE READ, PHANTOM READ

#### REPEATABLE READ

REPEATABLE READ는 InnoDB 엔진에서 사용하는 기본 격리 수준입니다. 따로 격리 수준을 설정해주지 않았다면 REPEATABLE READ 격리 수준으로 트랜잭션이 실행됩니다. REPEATABLE READ란 하나의 트랜잭션 내부에서 같은 SELECT문은 항상 같은 결과를 보여주는 것입니다. MySQL InnoDB에서는 트랜잭션별로 식별자를 주고 트랜잭션에서 변경하는 데이터를 UNDO영역에 백업합니다. 이 백업된 데이터와 트랜잭션 식별자로 동일 트랜잭션에서 동일 결과값을 보여줄 수 있도록 보장합니다.

발생 가능한 문제점 - PHANTOM READ (InnoDB는 발생하지 않음)

#### SERIALIZABLE

격리수준중 가장 높은 격리 수준입니다. 레코드를 조회할 때 Shared Lock을 획득해야만 조회할 수 있고, 데이터를 변경할 때에는 Exclusive Lock을 획득해야만 변경할 수 있습니다. 즉 한 트랜잭션에서 사용하는 데이터는 다른 트랜잭션에서 접근할 수 없게 만듭니다. 데이터 정합성을 지키는 면은 가장 우수하지만 동시 처리 성능이 떨어집니다.

### 격리 수준 변경

```
set session transaction isolation level red uncommitted;
```