package com.imooc.dao;

import com.imooc.entity.UserToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTokenDaoTest {

    @Autowired
    private UserTokenDao userTokenDao;

    @Test
    public void create() {

        UserToken userToken = new UserToken();

        userToken.setTokenId("0");

        userToken.setUserId("0");

        userToken.setToken("78789");

        UserToken result = userTokenDao.save(userToken);

        System.out.println(result.toString());
    }

    @Test
    public void findByUserIdTest() {

        UserToken userToken = userTokenDao.findByUserId("0");

        System.out.println(userToken.toString());
    }
}