package com.bean.sell.service;

import com.bean.sell.entity.ProductInfo;

import java.util.List;

public interface IProductInfoService {

    ProductInfo get(String id);
    List<ProductInfo> findAll(ProductInfo productInfo);
    boolean save(ProductInfo productInfo);
    boolean update(ProductInfo productInfo);
    void increaseStock(String id, int quantity);
    void decreaseStock(String id, int quantity);
}
