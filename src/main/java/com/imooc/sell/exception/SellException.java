package com.imooc.sell.exception;

import com.imooc.sell.enums.ResultEnum;

/**
* @ClassName: SellException
* @Description: 自定义异常类，要继承运行时异常
* @author ruimeng
* @date 2018年6月2日 上午9:28:28
*
*/ 

public class SellException extends RuntimeException {
	
	private Integer code ;

	//传入枚举进行构造
//注意：RuntimeException已经有了Msg属性的。 
	public SellException(ResultEnum resultEnum) {
		super(resultEnum.getMsg());
		this.code=resultEnum.getCode();
	}
	//注意构造方法的书写，父类中的一个参数的传值
	public SellException(Integer code,String msg){
		super(msg);
		this.code=code;
		;
	}

}
