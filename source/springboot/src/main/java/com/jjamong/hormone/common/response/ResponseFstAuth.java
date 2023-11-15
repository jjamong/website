package com.jjamong.hormone.common.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFstAuth {

   String accessToken;
   LocalDateTime expreDateTime;

}
