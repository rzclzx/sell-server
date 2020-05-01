package com.imooc.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

// entity jpa要用
@Entity
// data自动设置geter 、setter、构造函数和tostring
@Data
// 自动更新时间
@DynamicUpdate
// 自动设置时间注解前提
@EntityListeners(AuditingEntityListener.class)
public class ProductInfo {

    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private int productStock;

    private String productDesc;

    private String productIcon;

    private int categoryType;

    private int productStatus;

    // 自动设置时间
    @CreatedDate
    private Date createTime;

    // 自动更新时间
    @LastModifiedDate
    private Date updateTime;

}
