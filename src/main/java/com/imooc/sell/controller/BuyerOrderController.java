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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
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
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
							    		 @RequestParam(value ="page",defaultValue="0") Integer page,
							    		 @RequestParam(value ="size",defaultValue="10") Integer size){
    	
    	
    	//校验openid
    	if(StringUtils.isEmpty(openid)) {
    		logger.error("【查询订单列表】 openid为空");
    		throw new SellException(ResultEnum.FORM_RARAM_ERROOR);
    	}
    	PageRequest requset = new PageRequest(page,size);
		Page<OrderDTO> pageResult =	orderService.findOrderList(openid, requset);							
			
    	
    	return ResultVOUtil.success(pageResult.getContent());
    	
    }
    
    // 订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail (@RequestParam("openid") String openid,
    		                           @RequestParam("orderid") String orderid) {
    	//TODO
    	OrderDTO orderDTO = orderService.findOneOrder(orderid);
    	
				return ResultVOUtil.success(orderDTO) ;
						    	
    }
    
    
    // 取消订单
    @PostMapping("/cancel")
    public  ResultVO cancel (@RequestParam("openid") String openid,
            @RequestParam("orderid") String orderid) {
			//TODO 不安全做法待改进
			OrderDTO orderDTO = orderService.findOneOrder(orderid);
			orderService.cancelOrder(orderDTO);
			return ResultVOUtil.success() ;
    }
    
}
