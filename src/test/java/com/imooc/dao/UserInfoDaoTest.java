package com.imooc.dao;

import com.imooc.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserInfoDaoTest {

    @Autowired
    private UserInfoDao userInfoDao;

    @Test
    public void createTest() {

        UserInfo userInfo = new UserInfo();

        userInfo.setUserId("0");

        userInfo.setUserName("zhangfei");

        userInfo.setPassword("123456");

        UserInfo result = userInfoDao.save(userInfo);

        System.out.println(result.toString());
    }
}