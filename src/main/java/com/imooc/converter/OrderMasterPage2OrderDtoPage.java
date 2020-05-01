package com.imooc.converter;

import com.imooc.dao.OrderDetailDao;
import com.imooc.dto.OrderDto;
import com.imooc.entity.OrderMaster;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMasterPage2OrderDtoPage {

    @Autowired
    private OrderDetailDao orderDetailDao;

    public Page<OrderDto> convert(Page<OrderMaster> orderMasterPage) {
        List<OrderMaster> masters = orderMasterPage.getContent();

        List<OrderDto> orderDtoList = masters.stream().map(item -> {
            OrderDto orderDto = new OrderDto();

            BeanUtils.copyProperties(item, orderDto);

            orderDto.setOrderDetailList(orderDetailDao.queryOrderDetailsByOrderId(item.getOrderId()));

            return orderDto;
        }).collect(Collectors.toList());

        Pageable pageable = PageRequest.of(orderMasterPage.getPageable().getPageNumber(),
                orderMasterPage.getPageable().getPageSize());

        Page<OrderDto> orderDtoPage = new PageImpl<>(orderDtoList, pageable, orderMasterPage.getTotalElements());

        return orderDtoPage;
    }
}
