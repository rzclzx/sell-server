package com.imooc.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    FORM_ERROR(1,"参数不正确"),

    PRODUCT_NOT_EXIST(10, "商品不存在"),

    PRODUCT_NOT_ENOUGH(11,"商品库存不足"),

    ORDER_NOT_EXIST(12,"订单不存在"),

    ORDER_INFO_NOT_EXIST(13,"订单详情不存在"),

    ORDER_STATUS_ERROR(14,"订单状态不正确"),

    ORDER_UPDATE_ERROR(15,"订单更新失败"),

    ORDER_INFO_NULL(16,"订单详情为空"),

    ORDER_PAY_STATUS(17,"订单支付状态不正确"),

    CART_EMPTY(18,"购物车为空");

    private Integer code;

    private String Message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        Message = message;
    }
}
