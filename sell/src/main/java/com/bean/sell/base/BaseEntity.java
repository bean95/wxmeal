package com.bean.sell.base;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {

    protected String id;
    protected Date createDate;
    protected String createBy;
    protected Date updateDate;
    protected String updateBy;
    protected String delFlag;

    public void preInsert(){
        Date now = new Date();
        this.createDate = now;
        this.updateDate = now;
        this.delFlag = "0";
    }

    public void preUpdate(){
        this.updateDate = new Date();
    }


}
