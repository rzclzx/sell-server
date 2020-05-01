package com.imooc.service.impl;

import com.imooc.entity.ProductInfo;
import com.imooc.service.ProductInfoService;
import com.imooc.util.ListMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoImplTest {

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void queryProductInfo() {

        System.out.println(productInfoService.queryProductInfo("2").toString());
    }

    @Test
    public void queryProductInfos() {

        Pageable pageable = PageRequest.of(0,2);

        Page<ProductInfo> page = productInfoService.queryProductInfos(pageable);

        System.out.println(page.getTotalElements());
    }

    @Test
    public void queryProductInfosByStatus() {

        ListMap.listMapToString(productInfoService.queryProductInfosByStatus(0));
    }

    @Test
    public void saveProductInfo() {

        ProductInfo productInfo = new ProductInfo();

        productInfo.setProductId("3");

        productInfo.setProductName("拌面");

        productInfo.setProductPrice(new BigDecimal(12.55));

        productInfo.setProductStock(100);

        productInfo.setCategoryType(3);

        System.out.println(productInfoService.saveProductInfo(productInfo));
    }
}