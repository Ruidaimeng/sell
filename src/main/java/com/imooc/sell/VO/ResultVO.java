package com.imooc.sell.VO;

import lombok.Data;

/**
* @ClassName: ResultVO
* @Description: VO view Object 该包下，用于存放返回给视图的类
*     返回给页面的实体类，http请求返回的最外层对象
* @author ruimeng
* @date 2018年5月23日 下午8:45:53
*
*/ 

//注意这里Data注解没有起作用，导致没有get和set方法，这样controller，给页面返回对象是，无法序列化，出错。

@Data
public class ResultVO<T> {
	

	/**错误码 */
	private Integer code ; 
	
	/** 提示信息 */
	private String msg ;
	
	/** 返回的具体内容 */
	private T data;
	
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
