---
weight: 1
slug: index
date: 2021-07-03
title: "개발 환경 구성"
description: "프로젝트 개발 환경 구성해 보며 GIT에 대한 설정과 개발 환경 셋팅 가이드"
toc: true
---

프로젝트를 진행한다고 생각하고 GIT을 통해 개발 환경을 구축해 봅니다.<br>
github를 통해 리포지토리(repository)를 생성하고
develop(개발), stage(스테이지), prod(운영)으로 브랜치를 나누어 셋팅하는 등<br>
GIT을 통해 개발 환경을 설정해 보도록 하겠습니다.


## 깃허브 설정


### 리포지토리 생성

https://github.com/

깃허브 로그인을 하고 `webProject` 리포지토리를 생성합니다.

![repository](/docs/etc/git/devsetting/repository.png)

<br>

webProject 리포지토리 생성 완료

![github](/docs/etc/git/devsetting/github.png)


### 브랜치 생성

#### 개발(develop) 브랜치 생성
![branches_develop](/docs/etc/git/devsetting/branches_develop.png)

#### 스테이지(stage) 브랜치 생성
![branches_stage](/docs/etc/git/devsetting/branches_stage.png)

#### 운영(prod) 브랜치 생성
![branches_prod](/docs/etc/git/devsetting/branches_prod.png)

## 소스트리 설정

#### 개발(develop) 소스트리 설정
![sourcetree_develop](/docs/etc/git/devsetting/sourcetree_develop.png)

#### 스테이지(stage) 소스트리 설정
![sourcetree_stage](/docs/etc/git/devsetting/sourcetree_stage.png)

#### 운영(prod) 소스트리 설정
![sourcetree_prod](/docs/etc/git/devsetting/sourcetree_prod.png)


## 개발 작업사항 운영 브랜치까지 머지

메인페이지를 개발하여 운영에 배포힌다고 가정하고 진행해 보겠습니다.
<br>단, 깃에 관련한 작업만 있고 젠킨스 나 다른 배포과정은 다루지 않습니다.

개발자가 메인페이지를 개발하고 개발브랜치 깃에 커밋,푸시 합니다.<br>
이후, 스테이지에 머지, 운영에 머지합니다.

### 개발 브랜치 커밋&푸시

```
// index.html

<html>
    <head></head>
    <body>
        <!-- header -->
        <div class="header">
            <div class="gnb">
                <ul>
                    <li class="main">메인</li>
                    <li class="notice">공지사항</li>
                </ul>
            </div>
        </div>

        <!-- container -->
        <div class="container">
            <!-- 배너 영역 -->
            <div class="main_banner">
                <div>메인 슬라이드 배너</div>
            </div>

            <!-- 공지사항 -->
            <div class="notice">
                <div>공지사항</div>
            </div>

            <!-- 이미지 컨텐츠 -->
            <div class="image_content">
            </div>
            
            <!-- 이벤트 컨텐츠 -->
            <div class="event_content">
            </div>
        </div>

        <!-- footer -->
        <div class="footer">
        </div>
    </body>
</html>
```

메인페이지를 개발브랜치에 커밋&푸시 합니다.

![push_developer](/docs/etc/git/devsetting/push_developer.png)

<br>
푸시가 완료되고 각 브랜치 별로 확인해 보면 브랜치 별로 메인 페이지 개발이 올라가 있고
<br>
개발 브랜치의 `메인페이지 개발 커밋`이 푸시가 되어 있는 것을 확인 할 수 있습니다.

![branch_developer](/docs/etc/git/devsetting/branch_developer.png)

### 스테이지 브랜치 머지

스테이지 브랜치에서 병합을 선택하여 머지를 진행합니다.
<br>개발 브랜치의 `메인페이지 개발`의 커밋을 선택하고 병합하고 푸시합니다.

![merge_stage](/docs/etc/git/devsetting/merge_stage.png)

<br>
푸시가 완료되고 각 브랜치 별로 확인해 보면
<br>
스테이지 브랜치에 `메인페이지 개발 커밋`이 병합과 푸시가 되어 있는 것을 확인 할 수 있습니다.

![branch_stage](/docs/etc/git/devsetting/branch_stage.png)

### 운영 브랜치 머지

운영 브랜치에서 병합을 선택하여 머지를 진행합니다.
<br>스테이지 브랜치의 `메인페이지 개발`의 커밋을 선택하고 병합합니다.

![merge_prod](/docs/etc/git/devsetting/merge_prod.png)

<br>
병합이 완료되고 각 브랜치 별로 확인해 보면
<br>
운영 브랜치에 `메인페이지 개발 커밋`이 병합이 되어 있는 것을 확인 할 수 있습니다.

![branch_prod](/docs/etc/git/devsetting/branch_prod.png)