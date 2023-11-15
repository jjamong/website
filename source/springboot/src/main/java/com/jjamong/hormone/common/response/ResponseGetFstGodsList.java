package com.jjamong.hormone.common.response;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetFstGodsList {

   @JsonProperty("header")
   private Map<String, Object> header;
   private List<ResponseFstGodsList> data;
   private Map<String, Object> errorInfo;

}
