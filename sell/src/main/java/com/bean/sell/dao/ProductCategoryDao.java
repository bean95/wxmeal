package com.bean.sell.dao;

import com.bean.sell.VO.ProductVO;
import com.bean.sell.entity.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryDao {

    ProductCategory get(String id);

    List<ProductCategory> findAll(ProductCategory productCategory);

    int insert(ProductCategory productCategory);

    List<ProductVO> findAllInfoByCategory();
}





