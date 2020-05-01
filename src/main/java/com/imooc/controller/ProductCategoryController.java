package com.imooc.controller;

import com.imooc.VO.Result;
import com.imooc.entity.ProductCategory;
import com.imooc.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product/category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("list")
    public Result list() {

        Result res = new Result();

        try {
            List<ProductCategory> list = productCategoryService.queryProductCategories();
            res.setData(list);
            res.setCode(0);
            res.setMsg("success");
        } catch (Exception e) {
            e.printStackTrace();
            res.setCode(500);
            res.setMsg("false");
        }

        return res;
    }
}
