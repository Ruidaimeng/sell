package com.imooc.sell.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CarDTO;

/**
* @ClassName: ProductService
* @Description: 商品的业务层接口
* @author ruimeng
* @date 2018年5月23日 下午8:00:41
*
*/ 
public interface ProductService {

	 ProductInfo findOne(String productId);
	 
	/**
	* @Description: 查询所有商品
	* @param @return
	* @return List<ProductInfo>    返回类型
	* @throws
	*/ 
	List<ProductInfo> findAll();
	
	 Page<ProductInfo>  findAll(Pageable pageable);
	 
	ProductInfo save(ProductInfo productInfo);
	
	//加库存
	void increaseStock(List<CarDTO> listCarDTO);
	
	
	//减库存
	void decreaseStock(List<CarDTO> listCarDTO); 
}
