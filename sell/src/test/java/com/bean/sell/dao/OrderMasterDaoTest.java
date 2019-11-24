package com.bean.sell.dao;

import com.bean.sell.entity.OrderMaster;
import com.bean.sell.enums.OrderStatusEnum;
import com.bean.sell.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Test
    public void get() {
        Assert.assertEquals("大懒虫",orderMasterDao.get("7b509e17d3074cdab3d30c91ff0f7ef8").getBuyerName());
    }

    @Test
    public void findList() {
        List<OrderMaster> list = orderMasterDao.findList(new OrderMaster());
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void insert() {
        OrderMaster o = new OrderMaster();
        o.setId(UUID.randomUUID().toString().replaceAll("-",""));
        o.setBuyerName("大懒虫");
        o.setBuyerPhone("13650255404");
        o.setBuyerAddress("程介西华兴东巷888房");
        o.setBuyerOpenid("622848openid6533521");
        o.setOrderAmount(98.88);
        o.setOrderStatus(OrderStatusEnum.NEW.getCode());
        o.setPayStatus(PayStatusEnum.NEW.getCode());
        o.setCreateDate(new Date());
        o.setCreateBy("admin");
        o.setUpdateDate(new Date());
        o.setUpdateBy("admin");
        o.setDelFlag("0");

        Assert.assertNotEquals(0, orderMasterDao.insert(o));
    }
}