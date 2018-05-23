package com.imooc.sell.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imooc.sell.dataobject.ProductInfo;


/**
* @ClassName: ProductInfoRepository
* @Description: 商品信息repository
* @author ruimeng
* @date 2018年5月23日 上午10:29:34
*
*/ 
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
	
	
	List<ProductInfo> findByProductStatus(Integer productStatus);

}
