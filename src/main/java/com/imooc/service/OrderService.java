package com.imooc.service;

import com.imooc.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    // 创建订单
    OrderDto create(OrderDto orderDto);

    // 查询单个订单
    OrderDto queryOrderDto(String orderId);

    // 查询订单列表个人
    Page<OrderDto> queryOrderDtoList(String buyerOpenid, Pageable pageable);

    // 查询订单列表所有
    Page<OrderDto> queryOrderDtoList(Pageable pageable);

    // 取消订单
    OrderDto cancel(OrderDto orderDto);

    // 完结订单
    OrderDto finish(OrderDto orderDto);

    // 支付订单
    OrderDto paid(OrderDto orderDto);
}
