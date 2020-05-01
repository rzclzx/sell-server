package com.imooc.dao;

import com.imooc.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenDao extends JpaRepository<UserToken, String> {

    UserToken findByUserId(String userId);
}
