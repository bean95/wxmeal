package com.bean.sell.entity;

import com.bean.sell.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderDetail extends BaseEntity {

    @JsonProperty("detailId")
    private String id;
    private String orderId;
    private String productId;
    private String productName;
    private Double productPrice;
    private Integer productQuantity;
    private String productIcon;
}
