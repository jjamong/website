package com.jjamong.hormone.user.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jjamong.hormone.common.util.CookieUtil;
import com.jjamong.hormone.common.util.CustomException;
import com.jjamong.hormone.common.vo.ErrorCode;
import com.jjamong.hormone.common.jwt.JwtProvider;
import com.jjamong.hormone.common.response.ResponseCommon;
import com.jjamong.hormone.common.response.ResponseToken;
import com.jjamong.hormone.user.request.RequestLogin;
import com.jjamong.hormone.user.service.UserService;
import com.jjamong.hormone.user.auth.PrincipalDetails;
import com.jjamong.hormone.user.auth.PrincipalDetailsService;
import com.jjamong.hormone.user.entity.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
public class UserController {

        private final PrincipalDetailsService loginService;

        private final JwtProvider jwtProvider;
        private final CookieUtil cookieUtil;
        private final UserService userService;

        @RequestMapping(value = "/login", method = { RequestMethod.POST })
        public ResponseEntity<ResponseCommon<Object>> login(@RequestBody RequestLogin requestLogin,
                        HttpServletResponse rep) {
                PrincipalDetails user = loginService.loadUserByUsername(requestLogin.getUserId());
                UserEntity userEntity = userService.getUserInfo(requestLogin.getUserId());
                loginService.checkUser(requestLogin.getUserId(), requestLogin.getUserPw());

                // ROLE_ANONYMOUS일 때
                if (!user.getAuthorities().toString().equals("[ROLE_USER]")) {
                        throw new CustomException(ErrorCode.UNAPPROVED_LOGIN);
                }

                HashMap<String, Object> map = new HashMap<String, Object>();
                HashMap<String, Object> jwt = new HashMap<String, Object>();
                ResponseToken responseToken = jwtProvider.createAllToken(user.getUsername(), user.getAuthorities());

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
                jwt.put("accessToken", responseToken.getAccessToken());
                jwt.put("accessTokenExpire", (simpleDateFormat.format(responseToken.getAccessTokenExpire())));
                map.put("jwt", jwt);

                System.out.println(">>> responseToken.getRefreshToken() : " + responseToken.getRefreshToken());
                Cookie cookie = cookieUtil.createCookie(responseToken.getRefreshToken());
                rep.addCookie(cookie);

                HashMap<String, Object> userInfo = new HashMap<String, Object>();

                userInfo.put("userSeq", userEntity.getUserSeq());
                userInfo.put("userId", user.getUsername());
                userInfo.put("status", userEntity.getStatus());

                map.put("user", userInfo);
                map.put("role", user.getAuthorities());

                ResponseCommon<Object> response = ResponseCommon.builder()
                                .code(300)
                                .msg("로그인 성공")
                                .data(map)
                                .build();

                return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        // AccessToken 확인
        @RequestMapping(value = "/auth", method = RequestMethod.POST)
        public ResponseEntity<ResponseCommon<Object>> getAuth(HttpServletRequest req) {
                HashMap<String, Object> map = userService.getAuth(req);

                ResponseCommon<Object> response = ResponseCommon.builder()
                                .code(0)
                                .msg("인증정보 가져오기")
                                .data(map)
                                .build();

                return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        @RequestMapping(value = "/reissue", method = { RequestMethod.GET })
        public ResponseEntity<ResponseCommon<Object>> reissue(HttpServletRequest req) throws JsonProcessingException {
                HashMap<String, Object> map = userService.reissue(req);

                ResponseCommon<Object> response = ResponseCommon.builder()
                                .code(0)
                                .msg("재발급 성공")
                                .data(map)
                                .build();

                return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        @RequestMapping(value = "/logout", method = { RequestMethod.POST })
        public ResponseEntity<ResponseCommon<Object>> logout(HttpServletRequest req, HttpServletResponse res,
                        @RequestBody String accessToken) {
                userService.logout(req, res, accessToken);

                ResponseCommon<Object> response = ResponseCommon.builder()
                                .code(301)
                                .msg("로그아웃 성공")
                                .build();

                return ResponseEntity.status(HttpStatus.OK).body(response);
        }
}
