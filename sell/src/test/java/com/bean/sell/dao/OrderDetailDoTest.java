package com.bean.sell.dao;

import com.bean.sell.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDoTest {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void get() {
    }

    @Test
    public void findList() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("94f0be41af84418794c4096bbdadd10b");
        List<OrderDetail> list = orderDetailDao.findList(orderDetail);
        Assert.assertEquals(2,list.size());
    }

    @Test
    public void insert() {
        OrderDetail o = new OrderDetail();
        o.setId(UUID.randomUUID().toString().replaceAll("-",""));
        o.setOrderId(UUID.randomUUID().toString().replaceAll("-",""));
        o.setProductId(UUID.randomUUID().toString().replaceAll("-",""));
        o.setProductIcon("****.png");
        o.setProductName("u=juu");
        o.setProductQuantity(3);
        o.setProductPrice(55.78);
        o.setCreateDate(new Date());
        o.setCreateBy("admin");
        o.setUpdateDate(new Date());
        o.setUpdateBy("admin");
        o.setDelFlag("0");
        Assert.assertNotEquals(0,orderDetailDao.insert(o));

    }
}