package com.imooc.dao;

import com.imooc.entity.OrderDetail;
import com.imooc.util.ListMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void saveTest() {

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setDetailId("1");

        orderDetail.setOrderId("0");

        orderDetail.setProductId("1");

        orderDetail.setProductName("烧鸭饭");

        orderDetail.setProductPrice(new BigDecimal(12));

        orderDetail.setProductQuantity(1);

        OrderDetail result = orderDetailDao.save(orderDetail);

        System.out.println(result.toString());
    }

    @Test
    public void queryOrderDetailsByOrderIdTest() {

        List<OrderDetail> list = orderDetailDao.queryOrderDetailsByOrderId("0");

        ListMap.listMapToString(list);
    }

}