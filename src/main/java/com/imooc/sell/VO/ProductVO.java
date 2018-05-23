package com.imooc.sell.VO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
* @ClassName: ProductVO
* @Description: 返回给页面的商品信息类（包含类目）
* @author ruimeng
* @date 2018年5月23日 下午9:26:02
*
*/ 
public class ProductVO {

	@JsonProperty("name")  //json序列化时的key,变成name
	private String categoryName ;
	
	@JsonProperty("type")  //json序列化时的key,变成type
	private Integer categoryType ;
	
	@JsonProperty("foods")  //json序列化时的key,变成name
	private List<ProductInfoVO> productInfoVOList;
}
