---
weight: 1
date: 2020-09-02
title: "babel-plugin-module-resolver"
description: "리액트 네이티브(React Native)에 babel-plugin-root-import 플러그인에 대한 가이드"
toc: true
---

절대 경로로 컴포넌트를 추가하기 위한 플러그인

## 설치 및 설정

### 설치

yarn
```
$ yarn add --dev babel-plugin-module-resolver
```

### 설정

babel.config.js 파일 수정

```
module.exports = {
	presets: ['module:metro-react-native-babel-preset'],
	plugins: [
		[
			'module-resolver',
			{
				root: ['.'],
				extensions: ['.ios.ts', '.android.ts', '.ts', '.ios.tsx', '.android.tsx', '.tsx', '.jsx', '.js', '.json'],
				alias: {
					'~': './src',
				},
			},
		],
	],
};
```

## 사용 방법

`~/` src 경로로 인식

```
import Button from '~/Components/Button';
```