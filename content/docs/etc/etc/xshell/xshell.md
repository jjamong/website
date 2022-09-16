---
weight: 1
slug: index
date: 2022-09-16
title: "Xshell"
description: "Xshell"
toc: true
---


## Script

xshell은 로그인 스크립트를 사용할 수 있습니다.<br>
아래와 같이 로그인 스크립트 설정에서 연결 시 실행할 스크립트 파일을 연결합니다.

파일을 연결하면, Xshell로 서버 접속 후 연결된 스크립트 파일을 실행합니다.


![setting](/docs/etc/etc/xshell/setting.png)


```
// login1.vbs

Sub Main
	xsh.Screen.Send "su root"
	xsh.Screen.Send(VbCr) 
	xsh.Session.Sleep 500
	
	xsh.Screen.Send "1234"
	xsh.Screen.Send(VbCr) 
	xsh.Session.Sleep 500
End Sub
```

위와 같이 파일을 작성하고 접속하면,
서버 접속 시 자동으로 root로 계정으로 변환할 수 있습니다.