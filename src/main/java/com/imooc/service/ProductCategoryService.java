package com.imooc.service;

import com.imooc.entity.ProductCategory;

import java.util.List;


public interface ProductCategoryService {

    ProductCategory queryProductCategory(Integer id);

    List<ProductCategory> queryProductCategories();

    List<ProductCategory> queryProductCategoriesByType(int type);

    int saveProductCategory(ProductCategory productCategory);
}
