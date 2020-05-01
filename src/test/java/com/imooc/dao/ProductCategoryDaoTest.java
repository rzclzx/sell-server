package com.imooc.dao;

import com.imooc.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void findOneTest() {
        ProductCategory obj = productCategoryDao.findById(1).get();
        System.out.println(obj.toString());
    }

    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(2);
        productCategory.setCategoryName("我的最爱");
        productCategory.setCategoryType(3);
        productCategoryDao.save(productCategory);
    }
}