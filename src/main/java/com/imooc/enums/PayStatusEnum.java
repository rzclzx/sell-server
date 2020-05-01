package com.imooc.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {

    WAIT(0, "待支付"),
    SUCCESS(1, "已支付"),
    CANCEL(2,"已取消");

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;
}
