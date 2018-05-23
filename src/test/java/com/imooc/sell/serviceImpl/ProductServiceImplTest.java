package com.imooc.sell.serviceImpl;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
	
	@Autowired
	private ProductService service ;

	@Test
	public void testFindOne() {
		ProductInfo p =service .findOne("123456");
		//根据断言，判断测试是否成功，第一个为应该得出的值，第二个为实际测试获取到的值
       Assert.assertEquals("123456", p.getProductId());
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllPageable() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

}
