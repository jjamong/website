---
title: 퍼블리싱
date: 2020-01-19
description: D2 프로젝트는 NAVER D2 사이트를 리액트로 따라 만들며, 리액트로 웹사이트를 개발하며 공부하는 프로젝트입니다.
toc: true
slug: index
---

퍼블리싱은 [NAVER D2](https://d2.naver.com) 사이트에서 참조할 수 있는
HTML 마크업, CSS, FONT, IMAGE 등<br> 모든 것을 참조합니다.

D2 사이트를 보면 반응형 웹이지만, 반응형까지 모두 따라하진 않을 것이며,<br>
리액트의 순 기능을 사용할 정도의 기능이 동작하는 수준의 웹용만 퍼블리싱을 할 예정입니다.

D2 사이트를 열고 F12번을 눌러 개발자 도구를 켜 보세요!

## HTML 마크업

개발자 도구를 열고 엘리먼트를 선택하는 화살표 버튼을 선택하고 헤더 영역을 아래와
같이 선택해 주세요.


![01](/study/d2/01/publishing/01.png)

- - -

선택했다면, 그 상태에서 `F2`번을 눌러 HTML마크업을 복사하고 붙여넣기 식으로<br>
D2 사이트 페이지의 마크업을 참조할 수 있습니다.

![02](/study/d2/01/publishing/02.png)

- - -

![03](/study/d2/01/publishing/03.png)

## SASS/CSS

개발자 도구를 통해 엘리먼트를 선택하고 오른쪽 style탭에 나온 id,class명을 확인해 주세요.

![04](/study/d2/01/publishing/04.png)

- - -

위와 같이 스타일 속성을 복사하여 아래와 같이 sass에 적용하는 방식으로<br>
D2 사이트의 스타일 속성을 참조하여 구축합니다.

![05](/study/d2/01/publishing/05.png)
