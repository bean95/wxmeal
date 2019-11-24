package com.bean.sell.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {

    NEW("0", "未支付"),
    FINISHED("1","已支付")
    ;

    private String code;
    private String msg;

    PayStatusEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
