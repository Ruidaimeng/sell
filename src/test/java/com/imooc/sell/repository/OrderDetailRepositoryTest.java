package com.imooc.sell.repository;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.imooc.sell.dataobject.OrderDetail;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
	
	@Autowired
	private OrderDetailRepository repository;
	
	@Test
	public void testSave() {
		
		OrderDetail order = new OrderDetail();
		order.setDetailId("a124");
		order.setOrderId("123456");
		order.setProductId("123");
		order.setProductName("皮蛋瘦肉粥");
		order.setProductPrice(new BigDecimal("3.2"));
		order.setProductQuantity(3);
		order.setProductIcon("http://xxxxxxx.jpg");
		OrderDetail result = repository.save(order);
		Assert.assertNotNull(result);
		
	}

	@Test
	public void testFindByOrderId() throws Exception {
		
		 List<OrderDetail> result =repository.findByOrderId("123456");
		 Assert.assertEquals(1, result.size());
	}

}
