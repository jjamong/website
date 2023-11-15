package com.jjamong.hormone.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseNice {

   int iReturn;
   String sMessage;
   String sEncData;

}
