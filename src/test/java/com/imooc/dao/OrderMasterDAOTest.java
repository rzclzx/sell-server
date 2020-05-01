package com.imooc.dao;

import com.imooc.entity.OrderMaster;
import com.imooc.util.ListMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDAOTest {

    @Autowired
    private OrderMasterDAO orderMasterDAO;

    @Test
    public void saveOrderMaster() {

        OrderMaster orderMaster = new OrderMaster();

        orderMaster.setOrderId("1");

        orderMaster.setBuyerName("张飞");

        orderMaster.setBuyerAddr("硕丰圆小区");

        orderMaster.setBuyerOpenid("78979");

        orderMaster.setBuyerPhone("18879877979");

        orderMaster.setOrderAmount(new BigDecimal(200));

        orderMaster.setOrderStatus(0);

        orderMaster.setPayStatus(0);

        orderMasterDAO.save(orderMaster);
    }

    @Test
    public void queryOrderMasterByBuyerOpenidTest() {

        Pageable pageable = PageRequest.of(0,1);

        Page<OrderMaster> result = orderMasterDAO.queryOrderMasterByBuyerOpenid("78979", pageable);

        List<OrderMaster> list = result.getContent();

        ListMap.listMapToString(list);
    }
}