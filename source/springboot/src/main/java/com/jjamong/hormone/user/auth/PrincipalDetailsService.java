package com.jjamong.hormone.user.auth;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jjamong.hormone.common.util.CustomException;
import com.jjamong.hormone.common.vo.ErrorCode;
import com.jjamong.hormone.user.entity.UserEntity;
import com.jjamong.hormone.user.repository.UserRepository;

@Service
public class PrincipalDetailsService implements UserDetailsService {

   @Autowired
   private UserRepository userRepository;
   @Autowired
   private PasswordEncoder passwordEncoder;

   @Override
   public PrincipalDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
      UserEntity userEntity = userRepository.findByUserId(userId);

      return new PrincipalDetails(userEntity);
   }

   public boolean checkUser(String userId, String userPw) {
      UserEntity user = userRepository.findByUserId(userId);

      String realPw = user.getUserPw();
      boolean matches = passwordEncoder.matches(userPw, realPw);
      if (matches) {
         LocalDateTime now = LocalDateTime.now();
         user.setLoginDate(now);

         userRepository.save(user);
         return true;
      } else
         throw new CustomException(ErrorCode.IDANDPW_NOT_FOUND);
   }

}
