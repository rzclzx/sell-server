package com.imooc.service.impl;

import com.imooc.dao.ProductInfoDao;
import com.imooc.entity.ProductInfo;
import com.imooc.service.ProductInfoService;
import com.imooc.util.ImageUtil;
import com.imooc.util.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductInfoImpl implements ProductInfoService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public ProductInfo queryProductInfo(String id) {

        return productInfoDao.findById(id).get();
    }

    @Override
    public Page<ProductInfo> queryProductInfos(Pageable pageable) {

        return productInfoDao.findAll(pageable);
    }

    @Override
    public List<ProductInfo> queryProductInfosByStatus(int status) {

        return productInfoDao.findByProductStatus(status);
    }

    @Override
    public ProductInfo saveProductInfo(ProductInfo productInfo) {
        return productInfoDao.save(productInfo);
    }

    @Override
    public ProductInfo saveProductInfo(ProductInfo productInfo, MultipartFile file) {
        ProductInfo result = new ProductInfo();
        try {
            byte[] bytes = file.getBytes();
            String productId = KeyUtil.getUniqueKey();
            String extension = file.getOriginalFilename()
                    .substring(file.getOriginalFilename().indexOf(".") + 1);
            String path = ImageUtil.saveImg(extension, bytes, "product", productId);
            productInfo.setProductId(productId);
            productInfo.setProductIcon(path);
            result = productInfoDao.save(productInfo);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }
}
