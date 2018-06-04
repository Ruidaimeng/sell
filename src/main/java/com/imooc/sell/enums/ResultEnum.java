package com.imooc.sell.enums;


/**
* @ClassName: ResultEnum
* @Description: 结果类枚举
* @author ruimeng
* @date 2018年6月2日 上午9:30:00
*
*/ 
public enum ResultEnum {

	PRODUCT_NOT_EXIST(10,"商品不存在"),
	PRODUCT_STOCK_ERROR(11,"库存错误"),
	ORDER_NOT_EXIST(11,"商品不存在"),
	ORDER_UPDATE_FAIL(13,"订单状态更新失败"),
	ORDER_STATUS_EMPTY(14,"订单详情不存在"),
	ORDER_STATUS_ERROR(12,"商品不存在"),
	PAY_STATUS_ERROR(15	,"商品不存在"), PAY_UPDATE_FAIL(16	,"支付失败"),
	;

	private Integer code ;
	private String msg ;
	
	//要提供有参构造方法
		private ResultEnum(Integer code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
}
