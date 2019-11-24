package com.bean.sell.web;

import com.bean.sell.VO.ProductVO;
import com.bean.sell.VO.ResultVO;
import com.bean.sell.service.IProductCategoryService;
import com.bean.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/buyer/product")
@Slf4j
public class BuyerProductController {

    @Autowired
    IProductCategoryService productCategoryService;

    @GetMapping("list")
    public ResultVO list(HttpServletResponse response){

        //查询所有
        List<ProductVO>  t = productCategoryService.findAllInfoByCategory();
        log.info("【查询商品列表】");
        return ResultVOUtil.success(t);
    }
}
