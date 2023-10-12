---
weight: 1
slug: index
date: 2023-10-12
title: "Exception(익셉션)"
description: "Exception(익셉션) 가이드"
toc: true
---

@ControllerAdvice는 전역으로 exception handling을 할 수 있습니다.

## @ControllerAdvice 시작하기

### GlobalExceptionHandler

```
package com.jjamong.hormone.common.util.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jjamong.hormone.common.response.ResponseError;
import com.jjamong.hormone.common.util.CustomException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ResponseError> handleCustomException(final CustomException e) {
        return ResponseEntity
                .status(e.getErrorCode().getStatus().value())
                .body(new ResponseError(e.getErrorCode()));
    }

}
```

### ResponseError
```
package com.jjamong.hormone.common.response;

import org.springframework.http.ResponseEntity;

import com.jjamong.hormone.common.util.CustomException;
import com.jjamong.hormone.common.vo.ErrorCode;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ResponseError {

    private final String error;
    private final int code;
    private final String msg;

    public ResponseError(ErrorCode errorCode) {
        this.error = errorCode.getError();
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public static ResponseEntity<ResponseError> error(CustomException e) {
        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(ResponseError.builder()
                        .code(e.getErrorCode().getCode())
                        .msg(e.getErrorCode().getMsg())
                        .build());
    }
}
```

### ErrorCode
```
package com.jjamong.hormone.common.vo;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_REQUEST(HttpStatus.OK, "API오류", 101, "잘못된 요청입니다."),
    API_NOT_FOUND(HttpStatus.OK, "API오류", 102, "API 정보를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.OK, "API오류", 103, "허용되지 않은 메서드입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.OK, "API오류", 104, "내부 서버 오류입니다."),
    INPUT_ERROR(HttpStatus.OK, "API오류", 105, "입력 필드를 다시 확인해주세요."),
    DUPLICATION_ERROR(HttpStatus.OK, "API오류", 106, "중복 필드 존재"),
    DATABASE_ERROR(HttpStatus.OK, "API오류", 107, "데이터베이스 오류"),
    AUTH_ERROR(HttpStatus.OK, "API오류", 108, "권한이 없는 사용자입니다."),
    API_NOT_ALLOWED(HttpStatus.OK, "API오류", 109, "접근할 수 없습니다."),

    // TOKEN ERROR
    INVALID_TOKEN(HttpStatus.OK, "토큰오류", 201, "유효하지 않은 토큰입니다."),
    EXPRIATION_TOKEN(HttpStatus.OK, "토큰오류", 202, "인증 정보가 만료되었습니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.OK, "토큰오류", 203, "REFRESH TOKEN이 유효하지 않습니다."),
    INVALID_USER_TOKEN(HttpStatus.OK, "토큰오류", 204, "토큰의 유저 정보가 일치하지 않습니다."),
    FORBIDDEN_ERROR(HttpStatus.FORBIDDEN, "토큰오류", 403, "권한접근 에러"),

    // AUTH ERROR
    IDANDPW_NOT_FOUND(HttpStatus.OK, "회원오류", 301, "아이디나 또는 비밀번호를 다시 확인해주세요."),
    USER_NOT_FOUND(HttpStatus.OK, "회원오류", 302, "일치하는 회원이 없습니다."),
    NO_INPUT_PWIDERROR(HttpStatus.OK, "회원오류", 304, "아이디 또는 비밀번호를 입력해주세요."),
    UNAPPROVED_LOGIN(HttpStatus.OK, "회원오류", 311, "관리자 승인을 기다리고 있는 아이디입니다."),

    // IMAGE ERROR
    IMAGE_SERVER_ERROR(HttpStatus.OK, "이미지오류", 401, "서버 오류 : 이미지가 생성되지 않았습니다."),
    IMAGE_DELETE_ERROR(HttpStatus.OK, "이미지오류", 402, "서버 오류 : 이미지가 삭제되지 않았습니다."),
    IMAGE_ERROR(HttpStatus.OK, "이미지오류", 403, "이미지 형식이 맞지 않습니다."),
    IMAGE_INPUT_ERROR(HttpStatus.OK, "이미지오류", 405, "이미지 입력을 다시 확인해주세요."),
    // Order Error
    OVER_ORDER(HttpStatus.OK, "주문오류", 501, "소모품 신청이 불가능합니다."),

    // CMS ERROR
    INPUT_NURSE_ERROR(HttpStatus.OK, "CMS오류", 601, "간호사 정보를 등록해주세요."),

    // FASSTO ERROR
    FASSTO_SYSTEM_ERROR(HttpStatus.OK, "FASSTO오류", 801, "FASSTO TOKEN 오류"),
    FASSTO_BAD_ERROR(HttpStatus.OK, "FASSTO오류", 802, "Bad Request"),
    FASSTO_AUTH_ERROR(HttpStatus.OK, "FASSTO오류", 803, "Unauthorized"),
    FASSTO_UNKNOW_ERROR(HttpStatus.OK, "FASSTO오류", 804, "잘못된 경로"),
    FASSTO_DENY_ERROR(HttpStatus.OK, "FASSTO오류", 805, "접근 거부"),
    FASSTO_SERVER_ERROR(HttpStatus.OK, "FASSTO오류", 806, "서버 에러"),
    FASSTO_INPUT_ERROR(HttpStatus.OK, "FASSTO오류", 807, "단위 수량을 0개 이상 입력하세요."),
    FASSTO_PRODUCT_ERROR(HttpStatus.OK, "FASSTO오류", 808, "해당 상품의 재고가 부족합니다. 관리자에게 문의해주세요.");

    private final HttpStatus status;
    private final String error;
    private final int code;
    private final String msg;

}
```

### controller

```
package com.jjamong.hormone.user.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jjamong.hormone.common.response.ResponseCommon;
import com.jjamong.hormone.common.util.CustomException;
import com.jjamong.hormone.common.vo.ErrorCode;
import com.jjamong.hormone.user.request.RequestLogin;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
public class UserController {

        @RequestMapping(value = "/login", method = { RequestMethod.POST })
        public ResponseEntity<ResponseCommon<Object>> login(@RequestBody RequestLogin requestLogin,
                        HttpServletResponse rep) {

                if (true) {
                        throw new CustomException(ErrorCode.USER_NOT_FOUND);
                }

                return null;
        }

}
```