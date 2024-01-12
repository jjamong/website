---
weight: 1
slug: index
date: 2023-10-11
title: "Generic(제네릭)"
description: "Generic(제네릭)"
toc: true
---

## 이해하기

제네릭(Generic)은 클래스, 인터페이스, 메서드의 타입을 파라미터로 사용할수 있는 형태입니다.

<br>-- 제네릭 타입은 클래스 이름 뒤에 "<>" 부호가 붙고, 사이에 타입 파라미터가 위치합니다. `<T>`
<br>-- 타입 파라미터는 일반적으로 대문자 알파벳 한 글자로 표현합니다. (일반적으로 T 이나, A,B등 어떤것으로 해도 상관 없음)
<br>-- 제네릭을 통해 타입을 지정하게 됨으로써 타입 변환을 줄이게 되어 프로그램 성능에 유리합니다.