package com.bean.sell.service;

import com.bean.sell.entity.OrderDetail;
import com.bean.sell.entity.OrderMaster;
import com.bean.sell.enums.OrderStatusEnum;
import com.bean.sell.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterServiceImplTest {

    @Autowired
    private OrderMasterServiceImpl orderMasterServiceImpl;

    @Test
    public void create() {

        OrderMaster master = new OrderMaster();
        master.setBuyerName("Jason");
        master.setBuyerPhone("13650255404");
        master.setBuyerAddress("员村二横路");
        master.setBuyerOpenid("4409811995");

        List<OrderDetail> detailList = new ArrayList<>();
        OrderDetail d1 = new OrderDetail();
        d1.setProductId("6a3a81fd610a4553bb846c168b30c553");
        d1.setProductQuantity(1);
        OrderDetail d2 = new OrderDetail();
        d2.setProductId("79071482fcc94f0d8e5856bcda530a19");
        d2.setProductQuantity(200);
        detailList.add(d1);
        detailList.add(d2);
        master.setOrderDetailList(detailList);

        orderMasterServiceImpl.create(master);

    }

    @Test
    public void get() {
    }

    @Test
    public void findOne() {
        Assert.assertEquals(2,orderMasterServiceImpl.findOne("94f0be41af84418794c4096bbdadd10b").getOrderDetailList().size());
    }

    @Test
    public void findList() {
    }

    @Test
    public void findByOpenId() {
        Assert.assertEquals("员村二横路",orderMasterServiceImpl.findByOpenId("4409811995").get(0).getBuyerAddress());
    }

    @Test
    public void cancel() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setId("94f0be41af84418794c4096bbdadd10b");
        Assert.assertEquals("2",orderMasterServiceImpl.cancel(orderMaster).getOrderStatus());
    }

    @Test
    public void finish() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setId("94f0be41af84418794c4096bbdadd10b");
        Assert.assertEquals("1",orderMasterServiceImpl.finish(orderMaster).getOrderStatus());
    }

    @Test
    public void paid() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setId("94f0be41af84418794c4096bbdadd10b");
        Assert.assertEquals("1",orderMasterServiceImpl.paid(orderMaster).getPayStatus());
    }
}