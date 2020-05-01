package com.imooc.service;

import com.imooc.entity.ProductCategory;
import com.imooc.util.ListMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void queryProductCategoryTest() {
        ProductCategory productCategory = productCategoryService.queryProductCategory(1);

        System.out.println(productCategory.toString());
    }

    @Test
    public void queryProductCategoriesTest() {

        List<ProductCategory> list = productCategoryService.queryProductCategories();

        ListMap.listMapToString(list);
    }

    @Test
    public void queryProductCategoriesByTypeTest() {
        List<ProductCategory> list = productCategoryService.queryProductCategoriesByType(2);
        ListMap.listMapToString(list);
    }

    @Test
    public void saveProductCategoryTest() {

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(2);
        productCategory.setCategoryName("衣服美妆");
        int id = productCategoryService.saveProductCategory(productCategory);
        System.out.println(id);
    }
}