---
weight: 1
slug: index
date: 2023-11-15
title: "로그인"
description: "로그인"
toc: true
---

## JWT, Redis, Security 활용

[백엔드 소스파일](http://localhost:1313/source/springboot.zip) 

### 프로세스

#### 로그인

로그인이 액세스토큰을 응답값에 보내서 브라우저 로컬스토리지에 저장하며,
<br>리프레쉬토큰은 응닶값 보내기 전 레디스에 리프레쉬토큰 저장 및 쿠키에 저장

```
[front]
1.1 토큰 조회
- 최초 로그인 시 토큰 없음
axiosClient.interceptors.request.use(
const token = getAccessToken();

1.2 /login request API 요청
    const fetchLogin = async ( userId:string, userPw:string ) => {

[back]
1.3 controller /login
    UserController login

1.4 servcie id/pw로 DB계정 확인 및 성공
    1.4.1 UserController login
        loginService.checkUser

1.5 jwt RefrehToekn 생성 (user, role, 생성시간, 만료시간, 시크릿 키)
    1.5.1 UserController login
        ResponseToken responseToken = jwtProvider.createAllToken(user.getUsername(), user.getAuthorities());
    1.5.2 JwtProvider createAllToken
        String refreshToken = createRefreshToken(userId, authorities);

1.6 jwt/redis 생성된 RefrehToekn redis set
    1.6.1 JwtProvider createAllToken
        redisProvider.setValues(userId, refreshToken, Duration.ofMillis(REFRESHTIME));

1.7 jwt jwt AccessToken 생성 (user, role, 생성시간, 만료시간, 시크릿 키)
    1.7.2 JwtProvider createAllToken
        createAccessToken(userId, authorities)

1.8 controller response body 값에 AccessToken, Cookie 값에 RefrehToekn
    1.8.1 UserController login
        map.put("jwt", jwt);
        Cookie cookie = cookieUtil.createCookie(responseToken.getRefreshToken());

[front]
1.9 /login response API 응답
    로그인 성공 후 로컬 스토리지에 AccessToken, AccessToken만료기간 등 설정
    setAccessToken

```

#### 계정정보 가져오기

엑세스토큰을 request로 전달하고
서버에서 엑세스토큰을 받아서 복호화?하여 id로 변환하고 id를 db에 검색하여 계정정보 가져옴

```
[front]
2.1 토큰 조회
    로컬스토리지에 토큰이 있다면, header 값에 'Bearer ${token}' 설정
    axiosClient.interceptors.request.use(
    const token = getAccessToken();

    
2.2 /auth request API 요청
    const fetchCertification = async (  ) => {

[back]
2.3 controller /auth
    2.3.1 UserController auth
        HashMap<String, Object> map = userService.getAuth(req);
    2.3.2 UserServiceImpl getAuth

2.4 jwt 헤더 토큰 가져오기
    2.4.1 UserServiceImpl getAuth
        - 헤더에서 token값 가져오기
        String bearerToken = jwtProvider.getHeaderToken(request);
    
2.5 jwt 토큰 검증	
    2.5.1 UserServiceImpl getAuth
        - 토큰값이 존재하고 앞에 bearer(비어러/소유자) 값이 있는지?
        // 헤더에 accessToken이 없을 경우
        if (!(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))) {
            throw new CustomException(ErrorCode.FORBIDDEN_ERROR);
        }
        bearerToken = bearerToken.substring(7);

        - 토큰값이 만료됬는지?
        // 만기된 accessToken일 경우
        if (jwtProvider.tokenValidation(bearerToken).equals(JwtResultType.EXPIRED_JWT)) {
            throw new CustomException(ErrorCode.EXPRIATION_TOKEN);
        }

2.6 jwt 토큰으로 계정 id 가져오기	
    2.4.1 UserServiceImpl getAuth
        String userId = jwtProvider.getUserId(bearerToken);


2.7 servcie id로 DB조회에서 계정 정보 가져오기
    2.5.1 UserServiceImpl getAuth
        UserEntity userEntity = userRepository.findByUserId(userId);

        
2.8 controller response body
    2.6.1 UserController auth
        map.put("userSeq", userEntity.getUserSeq());
        map.put("role", jwtProvider.getRole(bearerToken));
        return map;

[front]
2.9 /login response API 응답
```

#### 토큰 재발급

서버에서 쿠키값에 리프레쉬토큰을 가져와 토큰형태 검증 및 쿠키값에 있는 리프레쉬토큰값과 레디스에 있는 토큰값 비교하여 맞을 경우
엑세스토큰을 재발급하여 response 응답함

```
[front]
3.1 /reissue
    /login이 아니고, 응답 에러가 발생했으며, 응답 코드가 403일때 한번에 한해서만 호출됨.
    
    if (originalConfig.url !== '/api/user/login' && err.response) {
        if (status === 403 && !originalConfig._retry) {
        originalConfig._retry = true;

[back]
3.3 controller /reissue
    3.3.1 UserController reissue
        public HashMap<String, Object> reissue(HttpServletRequest request) {
    3.3.2 UserServiceImpl reissue

3.4 service 쿠키값 리프레쉬토큰 가져와서 검증
    
    3.4.1 쿠키에 있는 pkToken키에 담긴 토큰값 가져옴
    Cookie cookie = cookieUtil.getCookie(request);
    String refreshToken = cookie.getValue();
    
    3.4.2 토큰값 검증
    if (jwtProvider.tokenValidation(refreshToken) != JwtResultType.VALID_JWT) {

    3.4.3 리프레쉬토큰과 레디스에 넣은 리프레쉬토큰과 비교 하여 검증
    String refreshRedisToken = (String) redisTemplate.opsForValue().get(userId);
    if (!refreshToken.equals(refreshRedisToken)) {
    
    3.4.4 검증 모두 통과되면 액세스토큰 재생성
    String accessToken = jwtProvider.createAccessToken(userId, user.getAuthorities());
    
3.5 controller response body
    3.5.1 UserController reissue
    return ResponseEntity.status(HttpStatus.OK).body(response);
    
[front]
3.6 /reissue response API 응답
    3.6.1 서버에서 쿠키의리프레쉬토큰과 레디스레프레쉬토큰이 같지 않을 경우 및 에러발생할 경우
    브라우저 로컬스토리지, 쿠키 제거 후 login페이지 이동
    
    3.6.2 정상적으로 엑세스토큰이 생성되어 응답되면 로컬스토리지에 제 셋팅.
```