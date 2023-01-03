---
weight: 1
slug: index
date: 2021-05-30
title: "Batch Script(배치 스크립트)"
description: "Batch Script(배치 스크립트) 가이드"
toc: true
---

Windows 운영체제는 CMD(명령프롬프트)를 통해 프로그램을 할 수 있습니다.<br>
파일을 생성, 삭제, 읽기, 변경 등이 가능하고 tellnet, ipconfig 등 여러 명렁어를 다룰 수 있습니다.

Batch Script(배치 스크립트)는 cmd 명령어를 스크립트로 작성하여 실행할 수 있도록 지원하는 기능이고,<br>
보통 자동 프로그래밍(서버 재시작, 파일 자동 이동)등에 사용됩니다.

## 시작하기

아래와 같은 bat 확장명으로 파일 생성 및 코드 작성을 하고 파일을 실행합니다.
```
// test.bat

echo "test"
```

### 윈도우에서 linux서버로 접속하여 파일 가져오기(scp 활용)
```
@echo off

:: 변수
set date=%1
set logsDir=C:\workspace\logs
set bash=C:\programs\Git\git-bash.exe
set gzip=C:\workspace\logs\gzip.sh

:: 폴더
cd C:\workspace\logs
if not exist %logsDir%\%date%\ ( md %date% )

:: 운영1 - pc
winscp /command "option batch abort" "option confirm off" "option transfer binary" "open test:1234@1.2.3.4" "get /server/logs/catalina.out.%date%.gz %logsDir%\%date%\catalina.out.%date%.gz" "close" "exit"
if exist %logsDir%\%date%\catalina.out.%date% ( del %logsDir%\%date%\catalina.out.%date% )
if exist %logsDir%\%date%\1_pc_catalina.out.%date% ( del %logsDir%\%date%\1_pc_catalina.out.%date% )

%bash% -C %gzip% %logsDir%\%date%\catalina.out.%date%.gz

ren %logsDir%\%date%\catalina.out.%date% 1_pc_catalina.out.%date%

:: 운영1 - m
winscp /command "option batch abort" "option confirm off" "option transfer binary" "open test:1234@1.2.3.4" "get /server/logs/catalina.out.%date%.gz %logsDir%\%date%\catalina.out.%date%.gz" "close" "exit"
if exist %logsDir%\%date%\catalina.out.%date% ( del %logsDir%\%date%\catalina.out.%date% )
if not exist %logsDir%\%date%\1_m_catalina.out.%date% ( del %logsDir%\%date%\1_m_catalina.out.%date% )

%bash% -C %gzip% %logsDir%\%date%\catalina.out.%date%.gz

ren %logsDir%\%date%\catalina.out.%date% 1_m_catalina.out.%date%

:: 운영1 - adm
winscp /command "option batch abort" "option confirm off" "option transfer binary" "open test:1234@1.2.3.4" "get /server/logs/catalina.out.%date%.gz %logsDir%\%date%\catalina.out.%date%.gz" "close" "exit"
if exist %logsDir%\%date%\catalina.out.%date% ( del %logsDir%\%date%\catalina.out.%date% )
if exist %logsDir%\%date%\1_adm_catalina.out.%date% ( del %logsDir%\%date%\1_adm_catalina.out.%date% )

%bash% -C %gzip% %logsDir%\%date%\catalina.out.%date%.gz

ren %logsDir%\%date%\catalina.out.%date% 1_adm_catalina.out.%date%


:: 운영2 - pc
winscp /command "option batch abort" "option confirm off" "option transfer binary" "open test:1234@1.2.3.4" "get /server/logs/catalina.out.%date%.gz %logsDir%\%date%\catalina.out.%date%.gz" "close" "exit"
if exist %logsDir%\%date%\catalina.out.%date% ( del %logsDir%\%date%\catalina.out.%date% )
if exist %logsDir%\%date%\2_pc_catalina.out.%date% ( del %logsDir%\%date%\2_pc_catalina.out.%date% )

%bash% -C %gzip% %logsDir%\%date%\catalina.out.%date%.gz

ren %logsDir%\%date%\catalina.out.%date% 2_pc_catalina.out.%date%


:: 운영2 - m
winscp /command "option batch abort" "option confirm off" "option transfer binary" "open test:1234@1.2.3.4" "get /server/logs/catalina.out.%date%.gz %logsDir%\%date%\catalina.out.%date%.gz" "close" "exit"
if exist %logsDir%\%date%\catalina.out.%date% ( del %logsDir%\%date%\catalina.out.%date% )
if exist %logsDir%\%date%\2_m_catalina.out.%date% ( del %logsDir%\%date%\2_m_catalina.out.%date% )

%bash% -C %gzip% %logsDir%\%date%\catalina.out.%date%.gz

ren %logsDir%\%date%\catalina.out.%date% 2_m_catalina.out.%date%

echo "end"
```

## 기본 명령어


### echo

일반적으로 배치 파일 첫줄에 `@echo off`를 설정합니다.<br>
기본값은 on으로 되어 있음며 배치파일 실행 시 명령줄이 화면에 출력되지 않게 합니다.

```
// test.bat

@echo off

echo test
echo.
echo test2


// 결과
c:\workspace>test.bat
test

test2

c:\workspace>
```