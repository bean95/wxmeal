package com.bean.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    PARAM_ERROR(1,"参数不正确"),

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存不足"),
    ORDER_STATUS_ERROR(12,"订单状态不符"),
    ORDER_PAY_STATUS_ERROR(13,"订单支付状态不符"),
    ORDER_OWNER_ERROR(14,"该订单不属于当前用户")
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
