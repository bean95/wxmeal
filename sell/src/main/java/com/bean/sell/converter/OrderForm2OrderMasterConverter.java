package com.bean.sell.converter;

import com.bean.sell.entity.OrderDetail;
import com.bean.sell.entity.OrderMaster;
import com.bean.sell.enums.ResultEnum;
import com.bean.sell.exception.SellException;
import com.bean.sell.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderMasterConverter {

    public static OrderMaster converter(OrderForm orderForm){
        Gson gson = new Gson();
        OrderMaster master = new OrderMaster();
        master.setBuyerName(orderForm.getName());
        master.setBuyerPhone(orderForm.getPhone());
        master.setBuyerAddress(orderForm.getAddress());
        master.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> detailList = new ArrayList<>();
        try {
            detailList = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【对象转换】错误",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        master.setOrderDetailList(detailList);
        return master;
    }
}
