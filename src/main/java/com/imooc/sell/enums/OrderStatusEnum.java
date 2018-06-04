package com.imooc.sell.enums;


/**
* @ClassName: OrderStatusEnum
* @Description:订单状态枚举
* @author ruimeng
* @date 2018年5月31日 下午6:20:19
*
*/ 
public enum OrderStatusEnum implements CodeEnum {
	
	    NEW(0, "新订单"),
	    FINISHED(2, "完结"),
	    CANCEL(1, "已取消"),
	    ;

	    private Integer code;

	    private String message;

	    OrderStatusEnum(Integer code, String message) {
	        this.code = code;
	        this.message = message;
	    }

		@Override
		public Integer getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

}
