package com.bean.sell.service;

import com.bean.sell.dao.OrderDetailDao;
import com.bean.sell.dao.OrderMasterDao;
import com.bean.sell.entity.OrderDetail;
import com.bean.sell.entity.OrderMaster;
import com.bean.sell.entity.ProductInfo;
import com.bean.sell.enums.OrderStatusEnum;
import com.bean.sell.enums.PayStatusEnum;
import com.bean.sell.enums.ResultEnum;
import com.bean.sell.exception.SellException;
import com.bean.sell.utils.KeyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OrderMasterServiceImpl implements IOrderMasterService{

    @Autowired
    private IProductInfoService productInfoServiceImpl;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;


    @Override
    @Transactional
    public OrderMaster create(OrderMaster orderMaster) {

        OrderMaster master = new OrderMaster();
        String orderId = KeyUtil.genUUID();

        Date now = new Date();
        Double total = 0.0;
        //查询商品的数量、单价 校验
        for(OrderDetail orderDetail : orderMaster.getOrderDetailList()){
            ProductInfo productInfo = productInfoServiceImpl.get(orderDetail.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            if(productInfo.getStock() < orderDetail.getProductQuantity()){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            //每件商品总价
            total += orderDetail.getProductQuantity() * productInfo.getPrice();

            //保存详情： 前端传值 ： productID + quantity
            //订单id
            orderDetail.setId(KeyUtil.genUUID());
            orderDetail.setOrderId(orderId);
            orderDetail.setProductPrice(productInfo.getPrice());
            orderDetail.setProductName(productInfo.getName());
            orderDetail.setProductIcon(productInfo.getIcon());
            orderDetail.preInsert();
            orderDetailDao.insert(orderDetail);

            //扣库存
            productInfoServiceImpl.decreaseStock(orderDetail.getProductId(),orderDetail.getProductQuantity());
        }

        //保存主订单数据
        BeanUtils.copyProperties(orderMaster,master);
        master.setId(orderId);
        master.setOrderAmount(total);
        master.preInsert();
        orderMasterDao.insert(master);

        return master;
    }

    @Override
    public OrderMaster get(String orderId) {
        return null;
    }

    @Override
    public OrderMaster findOne(String orderId) {
        OrderMaster master = orderMasterDao.get(orderId);
        OrderDetail detailCondition = new OrderDetail();
        detailCondition.setOrderId(master.getId());
        List<OrderDetail> detailList = orderDetailDao.findList(detailCondition);
        master.setOrderDetailList(detailList);
        return master;
    }

    @Override
    public List<OrderMaster> findList(OrderMaster orderMaster) {

        //PageHelper.startPage();

        return orderMasterDao.findList(orderMaster);
    }

    @Override
    public PageInfo<OrderMaster> findPage(OrderMaster condition, Integer pageNum, Integer pageSize){

        PageHelper.startPage(pageNum,pageSize);
        List<OrderMaster> list = orderMasterDao.findList(condition);
        PageInfo<OrderMaster> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public List<OrderMaster> findByOpenId(String openId) {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerOpenid(openId);
        return orderMasterDao.findList(orderMaster);
    }

    @Override
    @Transactional
    public OrderMaster cancel(OrderMaster orderMaster) {
        orderMaster = this.findOne(orderMaster.getId());
        //判断订单状态
        if(!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[取消订单] 当前订单不能取消，orderId={},orderStatus={}",orderMaster.getId(),orderMaster.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改状态
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        orderMasterDao.update(orderMaster);

        //恢复库存
        for(OrderDetail o : orderMaster.getOrderDetailList())
            productInfoServiceImpl.increaseStock(o.getProductId(),o.getProductQuantity());
        //如果已支付，退款
        if(orderMaster.getPayStatus().equals(PayStatusEnum.FINISHED.getCode())){
            //TODO
            log.info("退款.");
        }
        return orderMaster;
    }

    @Override
    @Transactional
    public OrderMaster finish(OrderMaster orderMaster) {
        //判断状态
        orderMaster = this.findOne(orderMaster.getId());
        //判断订单状态
        if(!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[订单完结] 当前订单不能取消，orderId={},orderStatus={}",orderMaster.getId(),orderMaster.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改状态
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterDao.update(orderMaster);
        return orderMaster;
    }

    @Override
    @Transactional
    public OrderMaster paid(OrderMaster orderMaster) {
        //判断状态
        orderMaster = this.findOne(orderMaster.getId());
        //判断订单状态
        if(!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[订单支付] 当前订单状态不正确，orderId={},orderStatus={}",orderMaster.getId(),orderMaster.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改支付状态
        if(!orderMaster.getPayStatus().equals(PayStatusEnum.NEW.getCode())){
            log.error("[订单支付] 当前订单支付状态不正确，orderId={},orderPayStatus={}",orderMaster.getId(),orderMaster.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        orderMaster.setPayStatus(PayStatusEnum.FINISHED.getCode());
        orderMasterDao.update(orderMaster);
        return orderMaster;
    }

    @Override
    public OrderMaster checkOwner(String orderId, String openId) {
        OrderMaster master = this.findOne(orderId);
        if(!openId.equals(master.getBuyerOpenid())){
            log.error("【订单权限问题】");
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return master;
    }
}
