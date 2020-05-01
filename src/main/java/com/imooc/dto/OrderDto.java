package com.imooc.dto;

import com.imooc.entity.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDto {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddr;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    private List<OrderDetail> orderDetailList;
}
