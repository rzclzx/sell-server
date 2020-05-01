package com.imooc.controller;

import com.imooc.VO.Result;
import com.imooc.VO.product.ProductVO;
import com.imooc.VO.product.ProductInfoVO;
import com.imooc.entity.ProductCategory;
import com.imooc.entity.ProductInfo;
import com.imooc.service.ProductCategoryService;
import com.imooc.service.ProductInfoService;
import com.imooc.util.KeyUtil;
import com.imooc.util.ResultConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("product/info")
public class ProductInfoController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("list")
    @Cacheable(cacheNames = "aaa", key = "123")
    public Result list() {

        List<ProductInfo> upList = productInfoService.queryProductInfosByStatus(0);

        List<ProductCategory> productCategories = productCategoryService.queryProductCategories();

        // 将所有的类别覆盖的返回的类ProductVO中
        List<ProductVO> productVOS = productCategories.stream().map(item -> {

            ProductVO productVO = new ProductVO();

            BeanUtils.copyProperties(item, productVO);

            return productVO;
        }).collect(Collectors.toList());

        productVOS.forEach(item -> {

            List<ProductInfoVO> productInfoVOS = new ArrayList<ProductInfoVO>();

            upList.forEach(val -> {

                if (val.getCategoryType() == item.getCategoryType()) {

                    ProductInfoVO productInfoVO = new ProductInfoVO();

                    BeanUtils.copyProperties(val, productInfoVO);

                    productInfoVOS.add(productInfoVO);
                }
            });

            item.setProjectInfies(productInfoVOS);
        });

        return ResultConfig.success(productVOS);

    }

    @PostMapping("create")
    // 使用下面方法接收前端form-data请求
    public Result create(HttpServletRequest request) {

        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);

        String name = params.getParameter("name");

        BigDecimal price = new BigDecimal(params.getParameter("price"));

        String desc = params.getParameter("desc");

        Integer stock = Integer.valueOf(params.getParameter("stock"));

        Integer categoryType = Integer.valueOf(params.getParameter("categoryType"));

        ProductInfo productInfo = new ProductInfo();

        productInfo.setProductId(KeyUtil.getUniqueKey());

        productInfo.setProductName(name);

        productInfo.setProductPrice(price);

        productInfo.setProductDesc(desc);

        productInfo.setProductStock(stock);

        productInfo.setCategoryType(categoryType);

        MultipartFile file = params.getFiles("file").get(0);

        ProductInfo saveResult = productInfoService.saveProductInfo(productInfo, file);

        return ResultConfig.success(saveResult);

    }
}
