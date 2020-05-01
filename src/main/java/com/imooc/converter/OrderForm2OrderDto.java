package com.imooc.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imooc.dto.OrderDto;
import com.imooc.entity.OrderDetail;
import com.imooc.form.OrderForm;

import java.util.ArrayList;
import java.util.List;

//public class OrderForm2OrderDto {
//
//    public static OrderDto convert(OrderForm orderForm) {
//
//        Gson gson = new Gson();
//
//        OrderDto orderDto = new OrderDto();
//
//        orderDto.setBuyerName(orderForm.getName());
//
//        orderDto.setBuyerPhone(orderForm.getPhone());
//
//        orderDto.setBuyerAddr(orderForm.getAddr());
//
//        orderDto.setBuyerOpenid(orderForm.getOpenid());
//
//        List<OrderDetail> orderDetailList = new ArrayList<>();
//
//        try {
//            orderDetailList = gson.fromJson(orderForm.getItems(),
//                    new TypeToken<List<OrderDetail>>(){}.getType());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        orderDto.setOrderDetailList(orderDetailList);
//
//        return orderDto;
//
//    }
//}
