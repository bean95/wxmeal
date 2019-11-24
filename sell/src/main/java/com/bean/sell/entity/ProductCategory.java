package com.bean.sell.entity;

import com.bean.sell.base.BaseEntity;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class ProductCategory extends BaseEntity {

    private String name;
    private Integer type;


}
