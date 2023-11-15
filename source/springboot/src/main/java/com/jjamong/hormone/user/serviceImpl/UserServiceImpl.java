package com.jjamong.hormone.user.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jjamong.hormone.common.jwt.JwtProvider;
import com.jjamong.hormone.common.jwt.JwtResultType;
import com.jjamong.hormone.common.util.CookieUtil;
import com.jjamong.hormone.common.util.CustomException;
import com.jjamong.hormone.common.vo.ErrorCode;
import com.jjamong.hormone.user.auth.PrincipalDetails;
import com.jjamong.hormone.user.auth.PrincipalDetailsService;
import com.jjamong.hormone.user.entity.UserEntity;
import com.jjamong.hormone.user.repository.UserRepository;
import com.jjamong.hormone.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final PrincipalDetailsService principalDetailsService;
    final UserRepository userRepository;

    final JwtProvider jwtProvider;
    final RedisTemplate redisTemplate;
    final CookieUtil cookieUtil;

    @Override
    public UserEntity getUserInfo(String userId) {
        if (userRepository.findByUserId(userId) == null)
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        return userRepository.findByUserId(userId);
    }

    @Override
    public UserEntity checkUser(HttpServletRequest request, String name, String mobile_no) {
        UserEntity user = userRepository.findByUserNameAndUserPhone(name, mobile_no);

        if (user == null) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        return user;
    }

    @Override
    public HashMap<String, Object> getAuth(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();

        String bearerToken = jwtProvider.getHeaderToken(request);
        // 헤더에 accessToken이 없을 경우
        if (!(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))) {
            throw new CustomException(ErrorCode.FORBIDDEN_ERROR);
        }
        bearerToken = bearerToken.substring(7);

        // 만기된 accessToken일 경우
        if (jwtProvider.tokenValidation(bearerToken).equals(JwtResultType.EXPIRED_JWT)) {
            throw new CustomException(ErrorCode.EXPRIATION_TOKEN);
        }
        String userId = jwtProvider.getUserId(bearerToken);
        UserEntity userEntity = userRepository.findByUserId(userId);

        map.put("userSeq", userEntity.getUserSeq());
        map.put("role", jwtProvider.getRole(bearerToken));

        return map;
    }

    @Override
    public HashMap<String, Object> reissue(HttpServletRequest request) {
        // 1. RefreshToken 가져오기
        Cookie cookie = cookieUtil.getCookie(request);
        String refreshToken = cookie.getValue();

        // 2. 토큰 유효성 검사
        if (jwtProvider.tokenValidation(refreshToken) != JwtResultType.VALID_JWT) {
            throw new CustomException(ErrorCode.INVALID_REFRESH_TOKEN);
        }

        // 3. redis에서 refreshToken 가져오기(Redis Key : userId)
        String userId = jwtProvider.getUserId(refreshToken);
        String refreshRedisToken = (String) redisTemplate.opsForValue().get(userId);
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (!refreshToken.equals(refreshRedisToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        // 4. Access Token 재발급
        HashMap<String, Object> map = new HashMap<String, Object>();
        HashMap<String, Object> jwt = new HashMap<String, Object>();

        PrincipalDetails user = principalDetailsService.loadUserByUsername(userId);
        String accessToken = jwtProvider.createAccessToken(userId, user.getAuthorities());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Date accessTokenExpire = new Date(System.currentTimeMillis() + jwtProvider.getAccessTokenExpiresTime());

        jwt.put("accessToken", accessToken);
        jwt.put("accessTokenExpire", (simpleDateFormat.format(accessTokenExpire)));
        map.put("jwt", jwt);

        return map;
    }

    @Override
    public boolean logout(HttpServletRequest req, HttpServletResponse res, String accessToken) {
        SecurityContextHolder.clearContext();
        Cookie refreshToken = cookieUtil.getCookie(req);

        // AccessToken 검증
        if (jwtProvider.tokenValidation(accessToken) == JwtResultType.INVALID_JWT) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        // // Access Token에서 Id을 가져옴
        // Authentication auth = jwtProvider.getAuthentication(accessToken);

        if (refreshToken != null) {
            // 쿠키 만료
            refreshToken.setMaxAge(0);
            res.addCookie(refreshToken);
            // redis 블랙리스트 생성
            Long expiration = jwtProvider.getAccessTokenExpiresTime();
            redisTemplate.opsForValue().set(accessToken, "logout", expiration,
                    TimeUnit.MILLISECONDS);
        }

        return true;
    }
}