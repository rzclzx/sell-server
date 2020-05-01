package com.imooc.controller;

import com.imooc.VO.Result;
import com.imooc.dto.OrderDto;
import com.imooc.enums.ResultEnum;
import com.imooc.form.OrderForm;
import com.imooc.form.OrderIdForm;
import com.imooc.service.OrderService;
import com.imooc.util.ResultConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    // 创建
    @PostMapping("create")
    public Result create(@Valid @RequestBody OrderForm orderForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultConfig.getRequestBodyError(bindingResult);
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setBuyerName(orderForm.getName());

        orderDto.setBuyerPhone(orderForm.getPhone());

        orderDto.setBuyerAddr(orderForm.getAddr());

        orderDto.setBuyerOpenid(orderForm.getOpenid());

        orderDto.setOrderDetailList(orderForm.getItems());

        OrderDto result = orderService.create(orderDto);

        Map<String, String> map = new HashMap<>();

        map.put("orderId", result.getOrderId());

        return ResultConfig.success(map);

    }

    // 查询列表
    @GetMapping("list")
    public Result list(@RequestParam(value = "openid", required = false) String openid,
                       @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {

        PageRequest pageRequest = PageRequest.of(pageNumber, size);

        Page<OrderDto> orderDtoPage = !StringUtils.isEmpty(openid)
                ? orderService.queryOrderDtoList(openid, pageRequest)
                : orderService.queryOrderDtoList(pageRequest);

        return ResultConfig.success(ResultConfig.getPageList(orderDtoPage));
    }

    // 查询
    @GetMapping("query")
    public Result query(@RequestParam(value = "orderId", required = true) String orderId) {

        OrderDto orderDto;

        try {
            orderDto = orderService.queryOrderDto(orderId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultConfig.error(ResultEnum.ORDER_NOT_EXIST.getCode(),
                    ResultEnum.ORDER_NOT_EXIST.getMessage());
        }

        return ResultConfig.success(orderDto);
    }

    // 取消
    @PostMapping("cancel")
    public Result cancel(@Valid @RequestBody OrderIdForm orderIdForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultConfig.getRequestBodyError(bindingResult);
        }

        OrderDto dto = new OrderDto();

        dto.setOrderId(orderIdForm.getOrderId());

        OrderDto orderDto = orderService.cancel(dto);

        return ResultConfig.success(orderDto);
    }

    // 完成
    @PostMapping("finish")
    public Result finish(@Valid @RequestBody OrderIdForm orderIdForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultConfig.getRequestBodyError(bindingResult);
        }

        OrderDto dto = new OrderDto();

        dto.setOrderId(orderIdForm.getOrderId());

        OrderDto orderDto = orderService.finish(dto);

        return ResultConfig.success(orderDto);
    }

    // 付款
    @PostMapping("paid")
    public Result paid(@Valid @RequestBody OrderIdForm orderIdForm,
                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultConfig.getRequestBodyError(bindingResult);
        }

        OrderDto dto = new OrderDto();

        dto.setOrderId(orderIdForm.getOrderId());

        OrderDto orderDto = orderService.paid(dto);

        return ResultConfig.success(orderDto);
    }
}
