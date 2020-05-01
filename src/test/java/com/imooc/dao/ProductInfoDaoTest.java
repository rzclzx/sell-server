package com.imooc.dao;

import com.imooc.entity.ProductInfo;
import com.imooc.util.ListMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void productInfoDaoTest() {

        ProductInfo productInfo = new ProductInfo();

        productInfo.setProductId("7");

        productInfo.setProductName("臭豆腐");

        productInfo.setProductPrice(new BigDecimal(5));

        productInfo.setProductStock(100);

        productInfo.setCategoryType(2);

        productInfo.setProductStatus(0);

        ProductInfo result = productInfoDao.save(productInfo);
    }

    @Test
    public void findOne() {

        ProductInfo productInfo = productInfoDao.findById("1").get();

        System.out.println(productInfo.toString());
    }

    @Test
    public void updateOne() {

        ProductInfo productInfo = productInfoDao.findById("1").get();

        productInfo.setProductPrice(new BigDecimal(10));

        ProductInfo result = productInfoDao.save(productInfo);

        System.out.println(result.toString());
    }

    @Test
    public void findByStatusTest() {

        List<ProductInfo> list = productInfoDao.findByProductStatus(0);

        ListMap.listMapToString(list);
    }
}