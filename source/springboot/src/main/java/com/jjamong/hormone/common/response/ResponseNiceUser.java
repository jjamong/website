package com.jjamong.hormone.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseNiceUser {

   String sCipherTime; // 복호화한 시간
   String sRequestNumber; // 요청 번호
   String sResponseNumber; // 인증 고유번호
   String sAuthType; // 인증 수단
   String sName; // 성명
   String sDupInfo; // 중복가입 확인값 (DI_64 byte)
   String sConnInfo; // 연계정보 확인값 (CI_88 byte)
   String sBirthDate; // 생년월일(YYYYMMDD)
   String sGender; // 성별
   String sNationalInfo; // 내/외국인정보 (개발가이드 참조)
   String sMobileNo; // 휴대폰번호
   String sMobileCo; // 통신사

}
