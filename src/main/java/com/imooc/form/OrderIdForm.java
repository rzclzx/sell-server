package com.imooc.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderIdForm {

    @NotEmpty(message = "订单id必填")
    private String orderId;
}
