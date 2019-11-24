package com.bean.sell.dao;

import com.bean.sell.entity.ProductInfo;
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
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void get() {
        ProductInfo productInfo = productInfoDao.get("e7ffc454f75641b2a3300e9e4055a904");
        Assert.assertEquals("e7ffc454f75641b2a3300e9e4055a904",productInfo.getId());
    }

    @Test
    public void findAll() {
        List<ProductInfo> all = productInfoDao.findAll(new ProductInfo());
        Assert.assertNotEquals(0,all.size());
    }

    @Test
    public void insert() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(UUID.randomUUID().toString().replaceAll("-",""));
        productInfo.setName("团圆饭");
        productInfo.setPrice(99.99);
        productInfo.setStock(100);
        productInfo.setDescription("麦辣鸡腿堡2份+板烧鸡腿堡2份+巨无霸1份+麦乐鸡20块+大可乐杯");
        productInfo.setIcon("****.png");
        productInfo.setStatus("0");
        productInfo.setType(1005);
        productInfo.setCreateDate(new Date());
        productInfo.setCreateBy("admin");
        productInfo.setUpdateDate(new Date());
        productInfo.setUpdateBy("admin");
        productInfo.setDelFlag("0");
        Assert.assertNotEquals(0,productInfoDao.insert(productInfo));
    }

    @Test
    public void update(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId("6a3a81fd610a4553bb846c168b30c553");
        productInfo.setName("鸿门宴001");
        productInfo.setPrice(88.33);
        productInfo.setStock(33);
        productInfo.setDescription("鱼虾蟹");
        productInfo.setIcon("**33**.png");
        productInfo.setStatus("3");
        productInfo.setType(0033);
        productInfo.setUpdateDate(new Date());
        productInfo.setUpdateBy("admi33n");
        productInfo.setDelFlag("1");
        Assert.assertNotEquals(0,productInfoDao.update(productInfo));
    }
}