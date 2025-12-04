---
weight: 1
slug: index
date: 2025-12-03
title: "Swarm 시작하기"
description: "Swarm 시작하기"
toc: true
---

## 이해하기

### Swarm(스웜)?
Docker Swarm(도커 스웜)은 Docker가 제공하는 오케스트레이션(Orchestration) 도구야.
쉽게 말해서:

👉 여러 개의 Docker 컨테이너를 묶어서 클러스터처럼 운영하게 해주는 기술

Kubernetes(K8s)처럼 컨테이너를 여러 서버에 분산해서 돌리고, 장애가 나면 자동으로 다른 노드에 재배치하는 등의 기능을 제공해.

Docker가 만든 “간단한 쿠버네티스”
(쿠버네티스보다 가볍고 단순하지만 기능은 더 적음)

🧩 Docker Swarm이 할 수 있는 일

Docker Swarm은 다음을 자동으로 해줘:

✔ 컨테이너 클러스터 구성

여러 서버(Node)를 하나의 클러스터(Swarm)로 묶음

✔ 서비스(컨테이너) 자동 배포

docker stack deploy로 여러 개의 컨테이너를 여러 노드에 배포

✔ 로드밸런싱

서비스 replicas=3 → 3개의 컨테이너로 자동 분산 처리

✔ 장애 복구

노드가 죽으면 자동으로 다른 노드에 컨테이너 재생성

✔ 롤링 업데이트

버전업 시 하나씩 교체하며 배포


### Stack
스웜에서 사용하는 Compose 기반 “전체 애플리케이션 단위”
스웜 전용 compose 파일을 stack이라고 부르고
Service(서비스) — Swarm에서 실행되는 컨테이너 그룹(복제 포함)
Swarm = 하나의 파일(docker-stack.yml)

### 서비스
서비스(Service) Swarm에서 실행되는 컨테이너 그룹(복제 포함)


## 시작하기

### 스웜 초기화 (Swarm 클러스터에 가입)
```
$ docker swarm init
```

### 스웜 종료 (Swarm 클러스터에서 탈출)
```
docker swarm leave --force
```

### 스웜 확인
```
docker swarm ls
```

### 네트워크 생성
```
$ docker network create -d overlay net-spring
$ docker network create -d overlay net-db
```

### 네트워크 확인
```
docker network ls
docker network inspect net-db
```

### 네트워크 삭제
```
$ docker network rm net-db
```

### 스웜 노드 확인
```
$ docker node ls
```

### 스택 배포
```
# docker-stack.yml

version: "3.9"

services:
  db:
    image: postgres:15
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: 1234
      POSTGRES_DB: postgres
    ports:
      - "5432:5432"
    volumes:
      - pgdata:/var/lib/postgresql/data
    deploy:
      replicas: 1
      restart_policy:
        condition: on-failure

  spring:
    image: spring-backend:latest # build 대신 이미지 필요
    ports:
      - "8080:8080"
    depends_on:
      - db
    deploy:
      replicas: 2
      restart_policy:
        condition: on-failure
      update_config:
        parallelism: 1
      placement:
        max_replicas_per_node: 2 # 노드가 1개이기 때문에 2개가 동일 노드에 실행됨

volumes:
  pgdata:

```

```
docker stack deploy -c docker-compose-swarm.yml dockerStack
```

### 스택 확인
```
$ docker stack ls
```

### 스택 삭제
```
$ docker stack rm dockerStack
```


## Service(서비스)

### 서비스 확인
```
$ docker service ls
```

### 서비스 업데이트 (스택배포시 이름이 같아 반영이 안될경우)
```
$ docker service update --force dockerStack_spring
```

### 서비스 스케일 0으로 초기화
```
$ docker service scale myapp_spring=0
$ docker service scale myapp_db=0
```

# 서비스 삭제
```
$ docker service rm myapp_spring
$ docker service rm myapp_db
```