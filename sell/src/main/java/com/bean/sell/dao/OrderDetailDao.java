package com.bean.sell.dao;

import com.bean.sell.entity.OrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailDao {

    OrderDetail get(String id);
    List<OrderDetail> findList(OrderDetail orderDetail);
    int insert(OrderDetail orderDetail);
}
