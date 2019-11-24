package com.bean.sell.service;

import com.bean.sell.dao.ProductInfoDao;
import com.bean.sell.entity.ProductInfo;
import com.bean.sell.enums.ResultEnum;
import com.bean.sell.exception.SellException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ProductInfoServiceImpl implements IProductInfoService{

    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public ProductInfo get(String id) {
        return productInfoDao.get(id);
    }

    @Override
    public List<ProductInfo> findAll(ProductInfo productInfo) {
        return productInfoDao.findAll(productInfo);
    }

    @Override
    public boolean save(ProductInfo productInfo) {
        return productInfoDao.insert(productInfo)>0?true:false;
    }

    @Override
    public boolean update(ProductInfo productInfo) {
        return productInfoDao.update(productInfo)>0?true:false;
    }

    @Override
    @Transactional
    public void increaseStock(String id, int quantity) {
        ProductInfo origin = productInfoDao.get(id);
        if(origin == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        Integer rest = origin.getStock() + quantity;
        origin.setStock(rest);
        origin.preUpdate();
        productInfoDao.update(origin);

    }

    @Override
    @Transactional
    public void decreaseStock(String id, int quantity) {
        ProductInfo origin = productInfoDao.get(id);
        if(origin == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        Integer rest = origin.getStock() - quantity;
        if(rest < 0){
            throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
        }
        origin.setStock(rest);
        origin.preUpdate();
        productInfoDao.update(origin);
    }
}
