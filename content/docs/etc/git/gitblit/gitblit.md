---
weight: 1
slug: index
date: 2022-12-19
title: "Git Blit (깃 블릿)"
description: "Git Blit (깃 블릿) 가이드"
toc: true
---


## 사용하기

### 저장소 생성

git blit에선 계정에 따른 저장소 ssh키 등을 관리 할 수 있습니다.
<br>새 저장소를 선택하여 저장소 등록 페이지로 이동 합니다.

![저장소01](/docs/etc/git/gitblit/01.png)

프로젝트엔 계정이 출력될 것이고 필요한 정보들을 모두 작성 및 선택 후
<br>생성을 선택하시면 저장소가 생성됩니다.

<br>

내 저장소에 등록된 git 저장소가 생성된 것을 확인할 수 있습니다.

![저장소02](/docs/etc/git/gitblit/02.png)


### GIT 클론

git 명령어로도 가능하고, 소스트리 등 프로그램으로도 연동이 가능합니다.

![저장소03](/docs/etc/git/gitblit/03.png)

<br>

소스트리로 선택해서 클론받게 되면 소스트리에서 위와 같이 에러 로그가 출력될 수 있습니다.

![클론](/docs/etc/git/gitblit/04.png)


#### SSH키 생성

```
$ ssh-keygen -t rsa -b 4096 -C "~"(메일 계정)
```

![키등록](/docs/etc/git/gitblit/05.png)

```
C:\Users\~\.ssh
```

rsa방식의 키가 생성되었습니다.

![키등록 확인](/docs/etc/git/gitblit/06.png)

<br>

#### 소스트리 키 등록

소스트리 > 도구 > 옵션
메뉴에서 ssh키를 등록 합니다.

![키등록 확인](/docs/etc/git/gitblit/07.png)

<br>

#### Git Blit에 SSH 키 등록

생성한 키에서 `id_rsa.pub` 파일의 내용을 복사해서 Git Blit 사이트 SSH 키에 등록합니다.

![키등록 확인](/docs/etc/git/gitblit/08.png)

<br>

#### ssh-rsa, ssh-dss 이슈 발생

클론을 했을때 아래와 같은 에러가 발생할 경우 ssh config 파일을 등록합니다.

![키등록 확인](/docs/etc/git/gitblit/09.png)

```
\\ .ssh\config

Host ~
HostName ~
User ~
IdentityFile ~/.ssh/id_rsa
IdentitiesOnly yes
PubkeyAcceptedAlgorithms +ssh-rsa
HostkeyAlgorithms +ssh-rsa
```

<br>

#### 클론 확인

위 과정을 거치면 정상적으로 클론이 가능해 집니다.

![클론 확인](/docs/etc/git/gitblit/10.png)