package com.bean.sell.service;

import com.bean.sell.VO.ProductVO;
import com.bean.sell.entity.ProductCategory;

import java.util.List;

public interface IProductCategoryService {

    ProductCategory get(String id);
    List<ProductCategory> findAll(ProductCategory productCategory);
    boolean save(ProductCategory productCategory);
    boolean update(ProductCategory productCategory);
    List<ProductVO> findAllInfoByCategory();
}
