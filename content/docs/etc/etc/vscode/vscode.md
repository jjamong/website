---
slug: index
date: 2020-05-22
title: "vscode(Visual Studio Code)"
description: "vscode"
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







## 플러그인

### 한글

`korea`를 검색하여 설치하고 재실행 하면 됩니다.

![korea](/docs/etc/etc/vscode/korea.png)
