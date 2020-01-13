---
title: Yarn
description: 얀(Yarn)은 패키지 매니저 프로그램이다. 가장 유명한 패치키 매니저 npm의 단점들을 보안하고자 페이스북, Exponent, 구글, Tilde의 엔지니어들이 함께 만든 패키지 매니저가 바로 yarn이다.
section_main: true
date: 2019-12-24
---

얀(Yarn)은 패키지 매니저 프로그램이다.
가장 유명한 패치키 매니저 npm의 단점들을 보안하고자
페이스북, Exponent, 구글, Tilde의 엔지니어들이 함께 만든 패키지 매니저가 바로 yarn이다.

`url` : [https://yarnpkg.com](https://yarnpkg.com)


## yarn eject

프로젝트 환경설정 파일들을 프로젝트 루트로 설정한다.
eject 명령어가 성공하면 config 폴더가 생성된다.

```
$ yarn eject
```


```
$ react-scripts eject
NOTE: Create React App 2+ supports TypeScript, Sass, CSS Modules and more without ejecting: https://reactjs.org/blog/2018/10/01/create-react-app-v2.html        

? Are you sure you want to eject? This action is permanent. (y/N) (설정파일을 추출할 것인가?)
// y (입력)

(...)

Staged ejected files for commit.

Please consider sharing why you ejected in this survey:
  http://goo.gl/forms/Bi6CZjk1EqsdelXk1

Done in 7191.46s.
```

- - -

프로젝트를 git repogitory 로 관리하고 있다면 안될 수 있다.(untracked 파일이나 커밋하지 않은 수정 내역이 있는 경우)
yarn ejcet시에 아래와 같이 커밋을 해달라고 에러가 발생 할 수 있다.

```
? Are you sure you want to eject? This action is permanent. Yes
This git repository has untracked files or uncommitted changes:

src/App.css
D src/App.js
D src/App.test.js
D src/index.css
M src/index.js
D src/logo.svg

error Command failed with exit code 1.
```

add를 해주고 커밋을 해주면 정상적으로 eject가 동작한다.

```
$ git add .
$ git commit -m "commit text"
```