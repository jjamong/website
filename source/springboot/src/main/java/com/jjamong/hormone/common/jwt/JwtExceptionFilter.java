package com.jjamong.hormone.common.jwt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jjamong.hormone.common.response.ResponseError;

import io.jsonwebtoken.JwtException;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
         throws ServletException, IOException {
      try {
         chain.doFilter(request, response); // JwtAuthenticationFilter로 이동
      } catch (JwtException ex) {
         // JwtAuthenticationFilter에서 예외 발생하면 바로 setErrorResponse 호출
         setErrorResponse(request, response);
      }
   }

   public void setErrorResponse(HttpServletRequest req, HttpServletResponse res) throws IOException {

      ResponseError error = ResponseError.builder().code(108).msg("권한이 없는 사용자입니다.").error("API오류").build();
      res.setContentType("application/json;charset=UTF-8");
      res.setStatus(HttpStatus.OK.value());
      res.setContentType(MediaType.APPLICATION_JSON_VALUE);
      ObjectMapper objectMapper = new ObjectMapper();
      String json = objectMapper.writeValueAsString(error);
      PrintWriter writer = res.getWriter();
      writer.write(json);
      writer.flush();
   }
}
