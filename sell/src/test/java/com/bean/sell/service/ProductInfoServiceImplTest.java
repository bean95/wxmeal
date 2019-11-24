package com.bean.sell.service;

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
public class ProductInfoServiceImplTest {

    @Autowired
    private IProductInfoService productInfoService;

    @Test
    public void get() {
        ProductInfo productInfo = productInfoService.get("e7ffc454f75641b2a3300e9e4055a904");
        Assert.assertEquals("皮蛋粥",productInfo.getName());
    }

    @Test
    public void findAll() {
        List<ProductInfo> all = productInfoService.findAll(new ProductInfo());
        Assert.assertNotEquals(0,all.size());
    }

    @Test
    public void insert() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(UUID.randomUUID().toString().replaceAll("-",""));
        productInfo.setName("service");
        productInfo.setPrice(9.99);
        productInfo.setStock(33);
        productInfo.setDescription("美味可口便宜~~");
        productInfo.setIcon("www.baidu.com");
        productInfo.setStatus("0");
        productInfo.setType(002);
        productInfo.setCreateDate(new Date());
        productInfo.setUpdateDate(new Date());
        productInfo.setUpdateBy("002");
        Assert.assertNotEquals(0,productInfoService.save(productInfo));
    }
}