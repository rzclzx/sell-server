package com.imooc.service.impl;

import com.imooc.controller.WebSocket;
import com.imooc.converter.OrderMasterPage2OrderDtoPage;
import com.imooc.dao.OrderDetailDao;
import com.imooc.dao.OrderMasterDAO;
import com.imooc.dto.OrderDto;
import com.imooc.entity.OrderDetail;
import com.imooc.entity.OrderMaster;
import com.imooc.entity.ProductInfo;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;
import com.imooc.service.ProductInfoService;
import com.imooc.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderMasterPage2OrderDtoPage orderMasterPage2OrderDtoPage;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDAO orderMasterDAO;

    @Autowired
    private WebSocket webSocket;

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
        String orderId = KeyUtil.getUniqueKey();

        BigDecimal orderAmount = new BigDecimal(0);
        // 1、查询商品（价格，数量）
        // 2、计算总价
        // 3、写入订单数据库
        // 4、扣除库存
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {

            ProductInfo productInfo = productInfoService.queryProductInfo(orderDetail.getProductId());

            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            BeanUtils.copyProperties(productInfo, orderDetail);

            orderDetail.setDetailId(KeyUtil.getUniqueKey());

            orderDetail.setOrderId(orderId);

            orderDetailDao.save(orderDetail);

            decreaseStock(productInfo, orderDetail.getProductQuantity());
        }

        OrderMaster orderMaster = new OrderMaster();

        orderDto.setOrderAmount(orderAmount);

        orderDto.setOrderId(orderId);

        orderDto.setOrderStatus(OrderStatusEnum.NEW.getCode());

        orderDto.setPayStatus(PayStatusEnum.WAIT.getCode());

        BeanUtils.copyProperties(orderDto, orderMaster);

        orderMasterDAO.save(orderMaster);

        webSocket.sendMessage("有新订单");

        return orderDto;
    }

    @Override
    public OrderDto queryOrderDto(String orderId) {
        OrderMaster orderMaster = orderMasterDAO.findById(orderId).get();

        OrderDto orderDto = new OrderDto();

        BeanUtils.copyProperties(orderMaster, orderDto);

        orderDto.setOrderDetailList(orderDetailDao.queryOrderDetailsByOrderId(orderId));

        return orderDto;
    }

    @Override
    public Page<OrderDto> queryOrderDtoList(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterDAO.queryOrderMasterByBuyerOpenid(buyerOpenid, pageable);

        return orderMasterPage2OrderDtoPage.convert(orderMasterPage);
    }

    @Override
    public Page<OrderDto> queryOrderDtoList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterDAO.findAll(pageable);

        return orderMasterPage2OrderDtoPage.convert(orderMasterPage);
    }

    @Override
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster orderMaster = orderMasterDAO.findById(orderDto.getOrderId()).get();

        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        if (orderMaster.getPayStatus().equals(PayStatusEnum.CANCEL.getCode())) {
            throw new SellException(ResultEnum.ORDER_PAY_STATUS);
        }
        if (orderMaster.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());

            orderMaster.setPayStatus(PayStatusEnum.CANCEL.getCode());

            orderMasterDAO.save(orderMaster);
        } else {
            // 退钱
        }
        BeanUtils.copyProperties(orderMaster, orderDto);
        return orderDto;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        OrderMaster orderMaster = orderMasterDAO.findById(orderDto.getOrderId()).get();

        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderMaster.setOrderStatus(OrderStatusEnum.FINISH.getCode());

        orderMasterDAO.save(orderMaster);

        BeanUtils.copyProperties(orderMaster, orderDto);

        return orderDto;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {
        OrderMaster orderMaster = orderMasterDAO.findById(orderDto.getOrderId()).get();

        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());

        orderMasterDAO.save(orderMaster);

        BeanUtils.copyProperties(orderMaster, orderDto);

        return orderDto;
    }

    // 减少商品库存
    private void decreaseStock(ProductInfo productInfo, Integer number) {

        Integer stock = productInfo.getProductStock() - number;
        System.out.println("=====" + stock);
        if (stock < 0) {
            System.out.println("===<0");
            throw new SellException(ResultEnum.PRODUCT_NOT_ENOUGH);
        } else {
            System.out.println("===>0");
            productInfo.setProductStock(stock);

            productInfoService.saveProductInfo(productInfo);
        }
    }
}
