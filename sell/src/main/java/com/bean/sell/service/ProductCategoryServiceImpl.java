package com.bean.sell.service;

import com.bean.sell.VO.ProductVO;
import com.bean.sell.dao.ProductCategoryDao;
import com.bean.sell.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements IProductCategoryService{

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public ProductCategory get(String id) {
        return productCategoryDao.get(id);
    }

    @Override
    public List<ProductCategory> findAll(ProductCategory productCategory) {
        return productCategoryDao.findAll(productCategory);
    }

    @Override
    public boolean save(ProductCategory productCategory) {
        return productCategoryDao.insert(productCategory)>0?true:false;
    }

    @Override
    public boolean update(ProductCategory productCategory) {
        return false;
    }

    @Override
    public List<ProductVO> findAllInfoByCategory() {
        return productCategoryDao.findAllInfoByCategory();
    }
}
