---
title: "프론트 매터"
section_main: true
date: 2019-10-27
weight: 3
---


## slug
아래와 같이 정의될 때, URL의 파일명을 대체 할 수 있다.

content/posts/old-post.md
```
---
title: New Post
slug: "new-post"
---
```
URL의 파일명 부분이 slug로 대체된다.
```
example.com/posts/new-post/
```

## url
원하는 URL로 설정 할  수 있다.

content/posts/old-url.md
```
---
title: Old URL
url: /blog/new-url/
---
```
URL 이 설정한 url로 변경된다.
```
https://example.com/blog/new-url/
```