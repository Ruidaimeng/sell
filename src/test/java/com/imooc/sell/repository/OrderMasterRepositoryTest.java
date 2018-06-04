package com.imooc.sell.repository;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.imooc.sell.dataobject.OrderMaster;



/**
* @ClassName: OrderMasterRepositoryTest
* @Description: 订单主表测试类
* @author ruimeng
* @date 2018年6月2日 上午7:40:08
*
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
	
	@Autowired
	private  OrderMasterRepository repository ;
	
	private final String OPENDID = "111111" ;
	
	@Test
	public void testSaveOrderMaster() {
		OrderMaster ordermaster = new OrderMaster();
		ordermaster.setBuyerName("大师兄");
		ordermaster.setBuyerAddress("崧润路804弄");
		ordermaster.setOrderId("123454");
		ordermaster.setBuyerPhone("13816570705");
		ordermaster.setBuyerOpenid(OPENDID);
		ordermaster.setOrderAmount(new BigDecimal("3.8"));
//		ordermaster.setCreateTime(new Date());
//		ordermaster.setUpdateTime(new Date());   //注解上加上动态插入，即可不用给出时间，自动更新
		OrderMaster result  =repository.save(ordermaster);
		
		Assert.assertNotNull(result);
	}

	@Test
	public void testFindByBuyerOpenid() {
		
		//PageRequest构造参数，page,size
		PageRequest reqquest = new PageRequest(0,1);
		Page<OrderMaster> page =repository.findByBuyerOpenid(OPENDID, reqquest);
		
		Assert.assertNotEquals(0, page.getContent().size());
		
		
	}

}
