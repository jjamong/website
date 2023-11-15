package com.jjamong.hormone.common.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jjamong.hormone.common.util.CookieUtil;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

   @Autowired
   private JwtProvider jwtProvider;
   @Autowired
   private CookieUtil cookieUtil;
   @Autowired
   private RedisProvider redisProvider;

   public JwtAuthenticationFilter(JwtProvider jwtProvider, CookieUtil cookieUtil, RedisProvider redisProvider) {
      this.jwtProvider = jwtProvider;
      this.cookieUtil = cookieUtil;
      this.redisProvider = redisProvider;
   }

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
         throws ServletException, IOException {

      String accessToken = resolveToken(request);

      if (accessToken != null && jwtProvider.tokenValidation(accessToken) == JwtResultType.VALID_JWT) {
         Authentication authentication = jwtProvider.getAuthentication(accessToken);
         SecurityContextHolder.getContext().setAuthentication(authentication);
      }
      filterChain.doFilter(request, response);
   }

   /** 토큰 정보 추출 */
   private String resolveToken(HttpServletRequest request) {
      String bearerToken = request.getHeader("Authorization");
      if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
         return bearerToken.substring(7);
      }
      return null;
   }
}