package com.jjamong.hormone.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jjamong.hormone.user.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserId(String userId);

    UserEntity findByUserNameAndUserPhone(String userName, String userPhone);
}
