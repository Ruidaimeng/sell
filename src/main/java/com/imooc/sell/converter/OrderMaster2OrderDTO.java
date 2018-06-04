package com.imooc.sell.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.imooc.sell.dataobject.OrderMaster;
import com.imooc.sell.dto.OrderDTO;

/**
* @ClassName: OrderMaster2OrderDTO
* @Description: 转换器，将OrderMaster 装换成OrderDTO
* @author ruimeng
* @date 2018年6月4日 下午7:00:18
*
*/ 
public class OrderMaster2OrderDTO {
	
	public static OrderDTO converter(OrderMaster orderMaster) {
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDTO);
		return orderDTO;
		
	}
	
	public static List<OrderDTO> converter(List<OrderMaster> orderMasterList) {
		
		List<OrderDTO> orderDTOList = new ArrayList();
		
   for (OrderMaster orderMaster : orderMasterList) {
	   
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDTO);
		orderDTOList.add(orderDTO);
		}
		
		return orderDTOList;
		
	}

}
