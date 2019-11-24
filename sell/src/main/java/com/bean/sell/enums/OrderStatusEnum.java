package com.bean.sell.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {

    NEW("0", "新订单"),
    FINISHED("1","已完结"),
    CANCEL("2","已取消"),
    ;

    private String code;
    private String msg;

    OrderStatusEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
