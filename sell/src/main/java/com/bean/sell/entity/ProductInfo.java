package com.bean.sell.entity;

import com.bean.sell.base.BaseEntity;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class ProductInfo extends BaseEntity {

    private String name;
    private Double price;
    private Integer stock;
    private String description;
    private String icon;
    private String status;
    private Integer type;
    //private ProductCategory productCategory;


}
