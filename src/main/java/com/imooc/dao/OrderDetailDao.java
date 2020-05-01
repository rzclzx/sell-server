package com.imooc.dao;

import com.imooc.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailDao extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> queryOrderDetailsByOrderId(String orderId);
}
