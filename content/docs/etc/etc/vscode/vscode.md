---
weight: 2
slug: index
date: 2020-05-22
title: "VSCode(Visual Studio Code)"
description: "VSCode 가이드"
toc: true
---

url : `https://code.visualstudio.com/`


## 설정

### 기본터미널 git bash로 설정

`ctrl + ,` 또는 `파일 > 기본 설정 > 설정` 설정으로 들어가서

```
terminal.integrated.shell.windows
```
을 입력하고 setting.json을 키고

```
"terminal.integrated.shell.windows": "D:\\program\\Git\\bin\\bash.exe",
```

이와 같이 git bash.exe 경로로 변경하고 재실행 합니다.

![gitbash](/docs/etc/etc/vscode/gitbash.png)


### JDK 설정



## 플러그인

### 한글

`korea`를 검색하여 설치하고 재실행 하면 됩니다.

- - 한글 언어 지원

![korea](/docs/etc/etc/vscode/korea.png)

### JAVA

`Java Extension Pack`를 검색하고 설치합니다.

- - JAVA 언어 지원
- - 디버그
- - 테스트 실행
- - Maven 관리

![Java_Extension_Pack](/docs/etc/etc/vscode/Java_Extension_Pack.png)


### Spring Boot

`Spring Boot Extension Pack`를 검색하고 설치합니다.

- - Spring 프레임워크 관련

![Spring_Boot_Extension_Pack](/docs/etc/etc/vscode/Spring_Boot_Extension_Pack.png)
