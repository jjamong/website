---
title: "Hugo Templating 소개"
date: 2019-11-07
weight: 1
toc: true
---

https://gohugo.io/templates/introduction/


Hugo는 템플릿의 기준으로 Go의 html/template 및 text/template 라이브러리를 사용한다.

## 기본 구문
Go 템플릿은 변수와 기능이 추가된 HTML파일이다.
Go Template 변수와 기능은 {{}}내에서도 이용할 수 있다.

### 미리 정의된 변수 액세스

미리 정의된 변수는 현재 범위에 이미 존재하는 변수 (아래의 변수 섹션의 `.Title` 예제처럼) 또는 사용자 지정 변수일 수 있음 (같은 섹션의 `$address` 예시처럼.)

```
{{ .Title }}
{{ $address }}
```

함수에 대한 매개변수는 공백으로 구분된다. 일반적인 구문은 다음과 같다.
```
{{ FUNCTION ARG1 ARG2 .. }}
```

다음 예에서는 입력이 1과 2인 add 함수를 호출한다.
```
{{ add 1 2 }}
```

### 메소드와 필드는 점 표기법을 통해 접근
콘텐츠의 앞부분에 정의된 페이지 매개변수 표시 줄에 액세스
```
{{ .Params.bar }}
```

### 괄호를 사용하여 항목을 그룹화할 수 있음
```
{{ if or (isset .Params "alt") (isset .Params "caption") }} Caption {{ end }}
```

## 변수

Page가 템플릿의 기본 범위 인 경우 현재 범위의 Title 요소 (. –"점")는 점 접두사 (.Title)로 간단히 액세스 할 수 있다.
```
<title>{{ .Title }}</title>
```

값을 사용자 정의 변수에 저장하고 나중에 참조할 수도 있다.

변수 앞에는 $를 붙여야 한다.

```
{{ $address := "123 Main St." }}
{{ $address }}
```

## 함수


### 예 1: 번호 추가
```
{{ add 1 2 }}
<!-- prints 3 -->
```

### 예 2: 번호 비교
```
{{ lt 1 2 }}
<!-- prints true (즉, 1이 2보다 작기 때문에) -->
```

두 예제 모두 Go Template의 산술 함수를 사용한다.


## 포함
다른 템플릿을 포함할 때는 액세스하는 데 필요한 데이터를 전달해야 한다.

템플릿 위치는 항상 Hugo 내의 layouts/디렉토리 에서 시작한다.

### Partial
```
{{ partial "header.html" . }}
```

### Template
```
{{ template "_internal/opengraph.html" . }}
```


## Logic 