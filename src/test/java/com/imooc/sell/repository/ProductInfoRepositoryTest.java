package com.imooc.sell.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



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
	public void testFindOne() {
		
	}

	

}
