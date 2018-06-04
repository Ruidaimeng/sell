package com.imooc.sell.serviceImpl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.imooc.sell.LoggerTest2;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.service.OrderService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
	
	 private Logger logger = LoggerFactory.getLogger(OrderServiceImplTest.class);
	
	@Autowired
	private OrderService service ;

    private final String BUYER_OPENID = "111111";

    private final String ORDER_ID = "1528108358308424130";

//	@Test
//	public void testCreateOrder() {
//		 OrderDTO orderDTO = new OrderDTO();
//	        orderDTO.setBuyerName("二师兄");
//	        orderDTO.setBuyerAddress("幕课网");
//	        orderDTO.setBuyerPhone("13816570705");
//	        orderDTO.setBuyerOpenid(BUYER_OPENID);
//
//	        //购物车
//	        List<OrderDetail> orderDetailList = new ArrayList<>();
//	        OrderDetail o1 = new OrderDetail();
//	        o1.setProductId("123");
//	        o1.setProductQuantity(1);
//
//	        OrderDetail o2 = new OrderDetail();
//	        o2.setProductId("123456");
//	        o2.setProductQuantity(2);
//
//	        orderDetailList.add(o1);
//	        orderDetailList.add(o2);
//
//	        orderDTO.setOrderDetailList(orderDetailList);
//
//	        OrderDTO result = service.createOrder(orderDTO);
//	        logger.info("【创建订单】result={}", result);
//	        Assert.assertNotNull(result);
//		
//		
//	}
//
//	@Test
//	public void testFindOneOrder() {
//		OrderDTO orderdto =service.findOneOrder(ORDER_ID);
//		 logger.info("【查询单个订单】result={}", orderdto);
//		 Assert.assertEquals(ORDER_ID,orderdto.getOrderId());
//	}
//
//	@Test
//	public void testFindOrderList() {
//		PageRequest request =  new PageRequest(0,2);
//		Page<OrderDTO> orderDTOPage = service.findOrderList(BUYER_OPENID, request);
//		Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
//	}
//
//	@Test
//	public void testCancelOrder() {
//		OrderDTO orderDTO = service.findOneOrder(ORDER_ID);
//		OrderDTO orderResult =service.cancelOrder(orderDTO);
//		Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), orderResult.getOrderStatus());
//	}

//	@Test
//	public void testFinishOrder() {
//		OrderDTO orderDTO = service.findOneOrder(ORDER_ID);
//		OrderDTO orderResult =service.finishOrder(orderDTO);
//		Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), orderResult.getOrderStatus());
//	
//	}

	@Test
	public void testPaidOrder() {
			OrderDTO orderDTO = service.findOneOrder(ORDER_ID);
	OrderDTO orderResult =service.paidOrder(orderDTO);
	Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), orderResult.getPayStatus());
	}

}
