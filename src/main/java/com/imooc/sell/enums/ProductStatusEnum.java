package com.imooc.sell.enums;


import lombok.Getter;


//商品状态枚举
//@Getter

/**
* @ClassName: ProductStatusEnum
* @Description: 商品状态枚举，要提供get，set方法
* @author ruimeng
* @date 2018年5月23日 下午8:19:26
*
*/ 
public enum ProductStatusEnum  {
    UP(0, "在架"),
    DOWN(1, "下架");
	

	private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}