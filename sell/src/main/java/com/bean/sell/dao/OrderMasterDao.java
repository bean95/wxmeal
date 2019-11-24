package com.bean.sell.dao;

import com.bean.sell.entity.OrderMaster;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMasterDao {

    OrderMaster get(String id);
    List<OrderMaster> findList(OrderMaster orderMaster);
    int insert(OrderMaster orderMaster);
    int update(OrderMaster orderMaster);
}
