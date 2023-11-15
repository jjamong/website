package com.jjamong.hormone.common.jwt;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jjamong.hormone.common.response.ResponseToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtProvider {

   private final RedisProvider redisProvider;
   private final RedisTemplate redisTemplate;
   private final ObjectMapper objectMapper;

   /*
    * ACCESS_TOKEN : 30분
    * REFRESH_TOKEN : 2일
    */

   private final long ACCESSTIME = 30 * 60 * 1000L;
   private final long REFRESHTIME = 14 * 24 * 60 * 60 * 1000L;

   @Value("${jwt.secret.key}")
   private String secretKey;
   private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

   @PostConstruct
   public void init() {
      secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
   }

   // header 토큰을 가져오는 기능
   public String getHeaderToken(HttpServletRequest request) {
      return request.getHeader("Authorization");
   }

   // 토큰 생성
   // @TODO : RefreshToken Redis에 저장
   public String createRefreshToken(String userId, Collection<? extends GrantedAuthority> authorities) {

      Claims claims = Jwts.claims().setSubject(userId);
      claims.put("role", authorities.stream().findFirst().get().toString());
      String refreshToken = Jwts.builder()
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setClaims(claims)
            .setExpiration(new Date(System.currentTimeMillis() + REFRESHTIME))
            .signWith(signatureAlgorithm.HS256, secretKey)
            .compact();
      return refreshToken;
   }

   public String createAccessToken(String userId, Collection<? extends GrantedAuthority> authorities) {
      Claims claims = Jwts.claims().setSubject(userId);
      claims.put("role", authorities.stream().findFirst().get().toString());
      String accessToken = Jwts.builder()
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setClaims(claims)
            .setExpiration(new Date(System.currentTimeMillis() + REFRESHTIME))
            .signWith(signatureAlgorithm.HS256, secretKey)
            .compact();

      return accessToken;
   }

   public ResponseToken createAllToken(String userId, Collection<? extends GrantedAuthority> authorities) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

      String refreshToken = createRefreshToken(userId, authorities);
      redisProvider.setValues(userId, refreshToken, Duration.ofMillis(REFRESHTIME));

      return new ResponseToken(createAccessToken(userId, authorities), refreshToken,
            new Date(System.currentTimeMillis() + ACCESSTIME));
   }

   // 토큰 검증
   public JwtResultType tokenValidation(String token) {
      try {
         Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
         return JwtResultType.VALID_JWT;
      } catch (ExpiredJwtException e) {
         return JwtResultType.EXPIRED_JWT;
      } catch (Exception e) {
         return JwtResultType.INVALID_JWT;
      }
   }

   // 토큰으로부터 받은 정보를 기반으노 Authentication 객체를 반환하는 메소드
   public Authentication getAuthentication(String token) {
      return new UsernamePasswordAuthenticationToken(getUserId(token), "",
            createAuthorityList(getRole(token)));
   }

   public Subject getSubject(String accessToken) throws JsonProcessingException {
      String subjectStr = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(accessToken).getBody().getSubject();
      return objectMapper.readValue(subjectStr, Subject.class);
   }

   // Calims 정보 가져오기
   public Claims getClaims(String token) throws ExpiredJwtException {
      Claims claims = Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token).getBody();
      return claims;
   }

   public String getUserId(String token) {
      return getClaims(token).getSubject();
   }

   public String getRole(String token) {
      return (String) Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody()
            .get("role", String.class);
   }

   // AccessToken Vaild Time
   public Long getAccessTokenExpiresTime() {
      return ACCESSTIME;
   }

}
