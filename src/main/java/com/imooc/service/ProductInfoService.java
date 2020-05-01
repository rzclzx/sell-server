package com.imooc.service;

import com.imooc.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductInfoService {

    ProductInfo queryProductInfo(String id);

    Page<ProductInfo> queryProductInfos(Pageable pageable);

    List<ProductInfo> queryProductInfosByStatus(int status);

    ProductInfo saveProductInfo(ProductInfo productInfo);

    ProductInfo saveProductInfo(ProductInfo productInfo, MultipartFile file);
}
