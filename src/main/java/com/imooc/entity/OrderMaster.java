package com.imooc.entity;

import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;

@Entity
@Data
public class OrderMaster {
    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddr;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    private Integer payStatus = PayStatusEnum.WAIT.getCode();
}
