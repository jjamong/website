package com.jjamong.hormone.common.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseData {

   @JsonProperty("header")
   private Map<String, Object> header;
   private Map<String, Object> data;
   private Map<String, Object> errorInfo;

}
