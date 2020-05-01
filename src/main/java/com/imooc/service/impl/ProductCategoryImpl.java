package com.imooc.service.impl;

import com.imooc.dao.ProductCategoryDao;
import com.imooc.entity.ProductCategory;
import com.imooc.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public ProductCategory queryProductCategory(Integer id) {

        ProductCategory productCategory = productCategoryDao.findById(id).get();
        return productCategory;
    }

    @Override
    public List<ProductCategory> queryProductCategories() {

        List<ProductCategory> list = productCategoryDao.findAll();
        return list;
    }

    @Override
    public List<ProductCategory> queryProductCategoriesByType(int type) {

        List<ProductCategory> categories = productCategoryDao.findByCategoryType(type);
        return categories;
    }

    @Override
    public int saveProductCategory(ProductCategory productCategory) {

        int id = productCategoryDao.save(productCategory).getCategoryId();
        return id;
    }
}
