package com.bean.sell.service;

import com.bean.sell.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl productCategoryService;

    @Test
    public void get() {
        ProductCategory productCategory = productCategoryService.get("296f3a63e08640efa09662ebef371c7b");
        Assert.assertEquals("沙姜焖鸡",productCategory.getName());
    }

    @Test
    public void findAll() {

    }

    @Test
    public void save() {

    }

    @Test
    public void update() {
    }
}