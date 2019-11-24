package com.bean.sell.service;

import com.bean.sell.entity.OrderMaster;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IOrderMasterService {

    //findByOpenId

    //创订单
    OrderMaster create(OrderMaster orderMaster);
    //查询单个订单
    OrderMaster get(String orderId);
    //查询单个订单+详情
    OrderMaster findOne(String orderId);
    //查询订单列表
    List<OrderMaster> findList(OrderMaster orderMaster);

    PageInfo<OrderMaster> findPage(OrderMaster condition, Integer pageNum, Integer pageSize);

    //查询某个用户订单列表
    List<OrderMaster> findByOpenId(String openId);
    //取消订单
    OrderMaster cancel(OrderMaster orderMaster);
    //完结订单
    OrderMaster finish(OrderMaster orderMaster);
    //支付订单
    OrderMaster paid(OrderMaster orderMaster);

    OrderMaster checkOwner(String orderId, String openId);

}
