package com.imooc.sell.util;

import java.util.Random;

public class KeyUtil {
	
	/**
	* @Description: 生成唯一主键
	* @param @return
	* @return String    返回类型
	* @throws
	*/ 
	
//注意加上synchronized关键字，防止并发时，产生相同的主键
	public  static synchronized String getUniqueKey() {
		
		Random random = new Random();	
		//生成随机6位数
		Integer number = random.nextInt(900000)+100000;
		
		return System.currentTimeMillis()+String.valueOf(number) ;
		
	}

}
