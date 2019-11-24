package com.bean.sell.web;

import com.bean.sell.VO.ResultVO;
import com.bean.sell.converter.OrderForm2OrderMasterConverter;
import com.bean.sell.entity.OrderMaster;
import com.bean.sell.enums.ResultEnum;
import com.bean.sell.exception.SellException;
import com.bean.sell.form.OrderForm;
import com.bean.sell.service.IOrderMasterService;
import com.bean.sell.utils.ResultVOUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private IOrderMasterService orderMasterServiceImpl;

    //下单： 创建订单
    @PostMapping("create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确, orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        OrderMaster master = OrderForm2OrderMasterConverter.converter(orderForm);

        OrderMaster result = orderMasterServiceImpl.create(master);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getId());
        return ResultVOUtil.success(map);
    }

    //订单列表
    @GetMapping("list")
    public ResultVO<List<OrderMaster>> list(@RequestParam("openId") String openId,
                                            @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        OrderMaster condition = new OrderMaster();
        condition.setBuyerOpenid(openId);
        PageInfo<OrderMaster> page = orderMasterServiceImpl.findPage(condition,pageNum,pageSize);
        return ResultVOUtil.success(page.getList());
    }

    //订单详情
    @GetMapping("detail")
    public ResultVO<OrderMaster> detail(@RequestParam("openId") String openId,
                                        @RequestParam("orderId") String orderId){
        //TODO
        OrderMaster master = orderMasterServiceImpl.checkOwner(orderId,openId);
        return ResultVOUtil.success(master);

    }

    //取消订单
    @PostMapping("cancel")
    public ResultVO cancel(@RequestParam("openId") String openId,
                           @RequestParam("orderId") String orderId){
        //TODO
        OrderMaster condition = orderMasterServiceImpl.checkOwner(orderId,openId);
        orderMasterServiceImpl.cancel(condition);
        return ResultVOUtil.success();
    }

}
