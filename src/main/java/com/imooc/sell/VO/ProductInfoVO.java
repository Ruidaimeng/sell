package com.imooc.sell.VO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
* @ClassName: ProductInfoVO
* @Description: 页面展示的商品详情
* @author ruimeng
* @date 2018年5月23日 下午9:36:01
*
*/ 
public class ProductInfoVO {
	
	 @JsonProperty("id")
	    private String productId;

	    @JsonProperty("name")
	    private String productName;

	    @JsonProperty("price")
	    private BigDecimal productPrice;

	    @JsonProperty("description")
	    private String productDescription;

	    @JsonProperty("icon")
	    private String productIcon;

		public String getProductId() {
			return productId;
		}

		public void setProductId(String productId) {
			this.productId = productId;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public BigDecimal getProductPrice() {
			return productPrice;
		}

		public void setProductPrice(BigDecimal productPrice) {
			this.productPrice = productPrice;
		}

		public String getProductDescription() {
			return productDescription;
		}

		public void setProductDescription(String productDescription) {
			this.productDescription = productDescription;
		}

		public String getProductIcon() {
			return productIcon;
		}

		public void setProductIcon(String productIcon) {
			this.productIcon = productIcon;
		}

}
