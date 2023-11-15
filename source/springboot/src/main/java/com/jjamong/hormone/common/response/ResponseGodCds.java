package com.jjamong.hormone.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGodCds {

   private Long godSeq;
   private String godNm;
   private String cstGodCd;
   private int ordQty;

}
