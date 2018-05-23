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

import com.imooc.sell.dataobject.ProductInfo;



/**
* @ClassName: ProductInfoRepositoryTest
* @Description: 产品信息单元测试
* @author ruimeng
* @date 2018年5月23日 上午10:51:29
*
*/ 
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
	
	@Autowired
	private ProductInfoRepository repository ;
	
	
	
	@Test
	public void testFindList() {
		
		List<ProductInfo> list = repository.findByProductStatus(0);
		Assert.assertNotEquals(0, list.size());
		
	}

	@Test
	public void testSave() {
		ProductInfo p = new ProductInfo();
		p.setCategoryType(1);
		p.setProductDescription("很好喝的粥");
		p.setProductName("皮蛋瘦肉粥");
		p.setProductPrice(new BigDecimal("3.20"));
		p.setProductStatus(0);
		p.setProductId("123456");
		p.setProductIcon("http:\\xxxxx.jpg");
		p.setProductStock(100);
		
		repository.save(p);
		
	}

	

}
