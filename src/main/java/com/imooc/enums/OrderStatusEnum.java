package com.imooc.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {

    NEW(0, "新订单"),
    FINISH(1, "已完成"),
    CANCEL(2, "已取消");

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;
}
