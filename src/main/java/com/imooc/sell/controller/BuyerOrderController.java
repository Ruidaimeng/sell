package com.imooc.sell.controller;

import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.converter.OrderForm2OrderDTOConverter;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.OrderForm;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

//买家订单
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    private Logger logger = LoggerFactory.getLogger(BuyerOrderController.class);

    @Autowired
    private OrderService orderService;
    //创建订单
    @PostMapping("/create")
    public ResultVO <Map<String,String>> create(@Validated OrderForm orderForm , BindingResult bindingResult){
            if(bindingResult.hasErrors()){
                logger.error("【创建订单】 参数不正确 ，orderForm = {}",orderForm);
                throw new SellException(ResultEnum.FORM_RARAM_ERROOR.getCode(),bindingResult.getFieldError().getDefaultMessage());
            }
        OrderDTO orderDTO= OrderForm2OrderDTOConverter.convert(orderForm);
            //购物车为空
            if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
                logger.error("【创建订单】 购物车不能为空 ，orderForm = {}",orderForm);
                throw new SellException(ResultEnum.CART_EMPTY_ERROR);
            }
            OrderDTO orderdtoResult = orderService.createOrder(orderDTO);
            Map<String,String> map = new HashMap<>();
            map.put("orderId",orderdtoResult.getOrderId());
          return ResultVOUtil.success(map);
    }

    // 订单列表
    // 订单详情
    // 取消订单
}
