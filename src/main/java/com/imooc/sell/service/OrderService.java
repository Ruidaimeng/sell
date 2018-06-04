package com.imooc.sell.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.imooc.sell.dto.OrderDTO;

/**
* @ClassName: OrderService
* @Description: 订单业务层接口
* @author ruimeng
* @date 2018年6月2日 上午9:00:06
*
*/ 
public interface OrderService {
	
 
	//创建订单 
	OrderDTO createOrder(OrderDTO orderDTO);
 //	查询单个点单  
	OrderDTO findOneOrder(String orderID);
 //	查询订单列表 
	Page<OrderDTO> findOrderList(String buyerId ,Pageable page);
 //	删除订单 
	OrderDTO cancelOrder(OrderDTO orderDTO);
 //	完结订单 
	OrderDTO finishOrder(OrderDTO orderDTO);
 //	支付订单
	OrderDTO paidOrder(OrderDTO orderDTO);
	
	
	
	
}
