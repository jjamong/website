---
weight: 1
slug: index
date: 2025-12-03
title: "Docker 시작하기"
description: "Docker 시작하기"
toc: true
---


## 이해하기

### Docker(도커)란?

도커(Docker)는 애플리케이션을 어디서나 동일하게 실행할 수 있도록 ‘컨테이너’라는 표준화된 실행 환경을 만들어주는 기술이에요.

#### 설치
windows <br>
docker desktop 설치 https://www.docker.com/products/docker-desktop/


### WSL이란? (Windows Subsystem for Linux)
WSL = 윈도우 안에서 리눅스를 실행할 수 있게 해주는 기능이에요.<br>
예전에는 리눅스 개발하려면, 듀얼 부팅, 가상머신(VMware, VirtualBox)
같은 무거운 방법이 필요했는데<br>

✨ WSL은 Windows 안에서 리눅스 환경을 아주 가볍게 바로 실행할 수 있는 기술입니다.

```
wsl -l -v

  NAME              STATE           VERSION
* docker-desktop    Running         2
```

| 용어                 | 설명                         |
| ------------------ | -------------------------- |
| **WSL**            | Windows에서 리눅스를 실행 가능한 기능   |
| **WSL2**           | 최신, 더 빠르고 정확한 리눅스 커널 기반 버전 |


왜 Docker가 WSL2를 필요로 할까?

도커는 원래 Linux 기술(컨테이너, cgroup, namespace) 기반이에요.
Windows는 리눅스가 아니라서 바로 실행할 수 없음.

### Docker Image(도커 이미지) 란?
앱을 실행하기 위한 ‘설계도 + 재료 세트’

도커 이미지 안에는 다음이 들어 있어요: <br>
-- 운영체제의 기본 틀 (예: 리눅스 최소 버전) <br>
-- 실행 언어/런타임 (Python, Node.js 등) <br>
-- 라이브러리, 패키지 <br>
-- 앱 소스코드 <br>
-- 환경 설정 <br>

### Container(컨테이너)란?
컨테이너 = Docker 이미지가 실제로 실행되고 있는 독립된 공간(환경)
즉,<br>
👉 이미지는 설계도,<br>
👉 컨테이너는 그 설계도로 만든 실제 실행 중인 애플리케이션.<br>

이미지로부터 실제 실행된 인스턴스(프로세스)
하나의 이미지로 컨테이너 여러 개 만들 수 있음
상태를 가짐 (로그, 파일, 실행 상태)


### Docker Compose(도커 컴포즈)가 뭐야?
Docker Compose = 여러 개의 컨테이너를 한 번에 관리하는 도구

한 프로젝트 안에는 보통:

Spring Boot backend 컨테이너

PostgreSQL DB 컨테이너

Redis 컨테이너

프론트엔드(React/Next.js) 컨테이너

Nginx 컨테이너

이 여러 개가 동시에 연결되어 돌아가야 하죠?

✨ 이걸 docker run으로 일일이 실행하려면 너무 복잡해요.

그래서 나온 게 docker-compose.yml!

여러 컨테이너를 로컬에서 한 번에 띄우는 도구
어떤 이미지를 쓸지
환경변수
볼륨
포트
컨테이너 간 관계
로컬 개발용
오케스트레이션 기능 없음
컨테이너 죽으면 자동 재시작 안 함


## Image(이미지)
docker desktop 실행


### 이미지 확인

```
$ docker images

IMAGE                   ID             DISK USAGE   CONTENT SIZE   EXTRA
spring-backend:latest   ef859d384cda        416MB          115MB
```

### 이미지 빌드
sptring boot 프로젝트 준비 후 root 경로에 Dockerfile 생성<br>
(확장명 없이 파일명은 반드시 Dockerfile)

```
# Dockerfile

# 1단계: Build
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app

COPY . .
RUN ./gradlew bootJar

# 2단계: Runtime
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
```

이미지 빌드
```
$ docker build -t spring-backend ./springbackend
```

### 이미지 삭제
```
$ docker rmi spring-backend:latest
```

### 이미지 다운로드
```
$ docker pull postgres:15
```

## Container(컨테이너)

### 컨테이너 확인
```
$ docker ps
$ docker ps -a (종료된 것도 포함)

CONTAINER ID   IMAGE            COMMAND               CREATED          STATUS          PORTS                                         NAMES
08a3470db291   spring-backend   "java -jar app.jar"   49 seconds ago   Up 49 seconds   0.0.0.0:8080->8080/tcp, [::]:8080->8080/tcp   bold_hopper
```

### 컨테이너 실행
```
$ docker run -d --name spring-backend -p 8080:8080 spring-backend:latest
```

```
$ docker run --name my-postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=1234 -p 5432:5432 -d postgres:15
```

### 컨테이너 로그 확인
```
$ docker logs -f 08a3470db291
```

### 컨테이너 삭제
```
$ docker rm 08a3470db291
```

## Docker Compose(도커 컴포즈)

### 프로젝트 기본 구조
```
project-root/
 ├─ docker-compose.yml
 ├─ spring/
 │   ├─ src/
 │   ├─ build.gradle
 │   └─ ...
 └─ (기타 파일)

```

### 컴포즈 실행
```
# docker-compose.yml
version: "3.9"

services:
  db:
    image: postgres:15
    container_name: postgres-db
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: 1234
      POSTGRES_DB: postgres
    ports:
      - "5432:5432"
    volumes:
      - pgdata:/var/lib/postgresql/data

  spring:
    image: spring-backend:latest # 이미 빌드된 이미지 사용
    container_name: spring-backend
    ports:
      - "8080:8080"
    depends_on:
      - db

volumes:
  pgdata:

```

```
$ docker compose up
```

### 컴포즈 종료
```
$ docker compose down
```


