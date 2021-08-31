---
weight: 1
slug: index
date: 2021-08-30
title: "PHP (Hypertext Preprocessor)"
description: "AWS PHP 가이드"
toc: true
---

## 시작하기

### 설치

```
$ sudo amazon-linux-extras install -y php7.2
```

### 확인

vi 에디터로 아파치 웹루트에 phpinfo.php 파일을 생성한다.
```
// phpinfo.php

<?php phpinfo() ?>
```

파일을 생성하고 웹서버를 재시작하고 접속한다.

![01](/docs/infra/aws/ec2/php/01.png)