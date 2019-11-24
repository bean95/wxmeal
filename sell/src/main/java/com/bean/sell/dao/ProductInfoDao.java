package com.bean.sell.dao;

import com.bean.sell.entity.ProductInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInfoDao {

    ProductInfo get(String id);
    List<ProductInfo> findAll(ProductInfo productInfo);
    int insert(ProductInfo productInfo);
    int update(ProductInfo productInfo);

}
