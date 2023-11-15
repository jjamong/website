package com.jjamong.hormone.common.response;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class ResponseToken {

   private String accessToken;
   private String refreshToken;
   private Date accessTokenExpire;

   public ResponseToken(String accessToken, String refreshToken, Date accessTokenExpire) {
      this.accessToken = accessToken;
      this.refreshToken = refreshToken;
      this.accessTokenExpire = accessTokenExpire;
   }

}
