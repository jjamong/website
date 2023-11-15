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
