package com.jjamong.hormone.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.jjamong.hormone.common.vo.ErrorCode;

@Service
public class CookieUtil {

    // 쿠키저장(RefreshToken)
    public Cookie createCookie(String value) {
        Cookie cookie = new Cookie("pkToken", value);
        cookie.setPath("/");
        // 2주
        cookie.setMaxAge(14 * 24 * 60 * 60);
        cookie.setHttpOnly(true);
        return cookie;
    }

    public Cookie createAdminCookie(String value) {
        Cookie cookie = new Cookie("pkAdmin_Token", value);
        cookie.setPath("/");
        // 2주
        cookie.setMaxAge(14 * 24 * 60 * 60);
        cookie.setHttpOnly(true);
        return cookie;
    }

    public Cookie getCookie(HttpServletRequest req) {
        final Cookie[] cookies = req.getCookies();
        if (cookies == null)
            throw new CustomException(ErrorCode.BAD_REQUEST);
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("pkToken"))
                return cookie;
        }
        return null;
    }

    public Cookie getAdminCookie(HttpServletRequest req) {
        final Cookie[] cookies = req.getCookies();
        if (cookies == null)
            throw new CustomException(ErrorCode.BAD_REQUEST);
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("pkAdmin_Token"))
                return cookie;
        }
        return null;
    }
}
