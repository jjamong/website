---
weight: 1
slug: index
date: 2019-12-24
title: "Yarn(얀)"
description: "yarn은 페이스북에서 만든 npm과 같은 기능을 수행할 수 있는 자바스크립트 패키지 매니저입니다.
yarn은 npm의 단점으로 꼽히는 속도, 안정성, 보안성 등을 개선하고자 개발되었습니다.
yarn의 동작 방식으로는 다운받은 패키지 데이터를 캐시(cache)에 저장하여,
중복된 데이터는 다운로드하지않고, 캐시에 저장된 파일을 활용합니다.
또한 여러개의 패키지를 설치할 때 병렬로 처리하기 때문에 이론적으로 npm에 비해 패키지 설치속도가 빠릅니다. (npm은 순차적)
npm은 패키지가 설치될 때 자동으로 코드와 의존성을 실행할 수 있도록 허용했습니다.
이 특징은 편리한 기능이지만 보장된 정책 없이 등록한 패키지가 존재할 수 있다는 점에서 안정성이 떨어집니다. 반면 yarn은 yarn.lock이나 package.json으로 부터 설치만 하며, yarn.lock은 모든 디바이스에 같은 패키지를 설치하는 것을 보장하기 때문에 버전의 차이로 인해 생기는 버그를 방지해줄 수 있습니다."
toc: true
---

yarn은 페이스북에서 만든 npm과 같은 기능을 수행할 수 있는 자바스크립트 패키지 매니저입니다.

yarn은 npm의 단점으로 꼽히는 속도, 안정성, 보안성 등을 개선하고자 개발되었습니다.

<br>

yarn의 동작 방식으로는 다운받은 패키지 데이터를 캐시(cache)에 저장하여,
중복된 데이터는 다운로드하지않고, 캐시에 저장된 파일을 활용합니다.

또한 여러개의 패키지를 설치할 때 병렬로 처리하기 때문에 이론적으로 npm에 비해 패키지 설치속도가 빠릅니다. (npm은 순차적)

<br>

npm은 패키지가 설치될 때 자동으로 코드와 의존성을 실행할 수 있도록 허용했습니다.

이 특징은 편리한 기능이지만 보장된 정책 없이 등록한 패키지가 존재할 수 있다는 점에서 안정성이 떨어집니다.

<br>

반면 yarn은 yarn.lock이나 package.json으로 부터 설치만 하며, yarn.lock은 모든 디바이스에 같은 패키지를 설치하는 것을 보장하기 때문에 버전의 차이로 인해 생기는 버그를 방지해줄 수 있습니다.

<br>

`url` : [https://yarnpkg.com](https://yarnpkg.com)

## 설치

npm 설치
```
$ npm install -g yarn
```

```
$ yarn --version
```


## 시작

### init

```
$ yarn init

yarn init v1.19.1
question name (sasscss):
question version (1.0.0):
question description:
question entry point (index.js):
question repository url:
question author:
question license (MIT):
question private:
success Saved package.json
Done in 10.55s.
```

명령어를 입력하면 `package.json`이 생성됩니다.

```
// package.json
{
  "name": "sasscss",
  "version": "1.0.0",
  "main": "index.js",
  "license": "MIT"
}
```






### eject

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

## 사용법

### global  

글로벌 패키지 추가
```
$ yarn global add [package]
```

글로벌fh 설치된 패키지 리스트
```
$ yarn global list
```

### 패키지 추가
```
$ yarn add [package]
```