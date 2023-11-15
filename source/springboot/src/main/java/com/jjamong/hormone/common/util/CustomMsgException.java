package com.jjamong.hormone.common.util;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomMsgException extends RuntimeException {

   private final HttpStatus status;
   private final String error;
   private final int code;
   private final String msg;

}
