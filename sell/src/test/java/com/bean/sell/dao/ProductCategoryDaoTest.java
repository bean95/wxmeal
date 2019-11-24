package com.bean.sell.dao;

import com.bean.sell.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void get(){
        System.out.println(productCategoryDao.get("17FB45B3566F4FFE9C9BA7DC03F5F9F3"));
    }

    @Test
    public void findList(){
        System.out.println(productCategoryDao.findAll(new ProductCategory()));
    }

    @Test
    public void save(){

        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(UUID.randomUUID().toString().replaceAll("-",""));
        productCategory.setName("多人套餐");
        productCategory.setType(1003);
        productCategory.setCreateDate(new Date());
        productCategory.setCreateBy("admin");
        productCategory.setUpdateDate(new Date());
        productCategory.setUpdateBy("admin");
        productCategory.setDelFlag("0");
        System.out.println(productCategoryDao.insert(productCategory));
    }

    @Test
    public void findAllInfoByCategory(){
        System.out.println(productCategoryDao.findAllInfoByCategory());
    }
}
