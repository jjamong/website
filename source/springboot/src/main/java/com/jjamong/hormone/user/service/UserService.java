package com.jjamong.hormone.user.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjamong.hormone.user.entity.UserEntity;

public interface UserService {

    UserEntity getUserInfo(String userId);

    UserEntity checkUser(HttpServletRequest request, String name, String mobile_no);

    HashMap<String, Object> getAuth(HttpServletRequest request);

    HashMap<String, Object> reissue(HttpServletRequest request);

    boolean logout(HttpServletRequest req, HttpServletResponse res, String accessToken);
}
