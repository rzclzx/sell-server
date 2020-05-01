package com.imooc.service.impl;

import com.imooc.dto.OrderDto;
import com.imooc.entity.OrderDetail;
import com.imooc.service.OrderService;
import com.imooc.util.ListMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void saveTest() throws Exception {
        OrderDto orderDto = new OrderDto();

        orderDto.setBuyerName("张飞");

        orderDto.setBuyerPhone("18878997897");

        orderDto.setBuyerAddr("涞源县");

        orderDto.setBuyerOpenid("666");

        orderDto.setOrderStatus(0);

        orderDto.setPayStatus(0);

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setProductId("1");

        orderDetail.setProductQuantity(3);

        List<OrderDetail> list = Arrays.asList(orderDetail);

        orderDto.setOrderDetailList(list);

        orderService.create(orderDto);
    }

    @Test
    public void queryOrderDtoTest() {
        OrderDto orderDto = orderService.queryOrderDto("1549178767392320961");

        System.out.println(orderDto);
    }

    @Test
    public void queryOrderDtoListTest() {
        Pageable pageable = PageRequest.of(0,10);

        Page<OrderDto> orderDtoPage = orderService.queryOrderDtoList(pageable);

        ListMap.listMapToString(orderDtoPage.getContent());
    }

    @Test
    public void cancelTest() {
        OrderDto orderDto = new OrderDto();

        orderDto.setOrderId("1");

        OrderDto result = orderService.cancel(orderDto);
    }

    @Test
    public void finishTest() {
        OrderDto orderDto = new OrderDto();

        orderDto.setOrderId("0");

        OrderDto result = orderService.finish(orderDto);
    }
}