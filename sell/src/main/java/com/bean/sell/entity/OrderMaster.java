package com.bean.sell.entity;

import com.bean.sell.base.BaseEntity;
import com.bean.sell.enums.OrderStatusEnum;
import com.bean.sell.enums.PayStatusEnum;
import com.bean.sell.utils.serializer.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderMaster extends BaseEntity {

    @JsonProperty("orderId")
    private String id;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private Double orderAmount;
    private String orderStatus = OrderStatusEnum.NEW.getCode();
    private String payStatus = PayStatusEnum.NEW.getCode();

    @JsonProperty("createTime")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createDate;

    @JsonProperty("updateTime")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateDate;

    List<OrderDetail> orderDetailList = new ArrayList<>();

}
