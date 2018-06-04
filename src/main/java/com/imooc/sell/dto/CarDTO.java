package com.imooc.sell.dto;


/**
* @ClassName: CarDTO
* @Description: 购物车实体类，
* @author ruimeng
* @date 2018年6月2日 上午10:10:45
*
*/ 
public class CarDTO {

	private String  prductId ;
	
	private Integer productQuantity;
	
	public String getPrductId() {
		return prductId;
	}
	public void setPrductId(String prductId) {
		this.prductId = prductId;
	}
	public Integer getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}
	public CarDTO(String prductId, Integer productQuantity) {
		super();
		this.prductId = prductId;
		this.productQuantity = productQuantity;
	}
}
