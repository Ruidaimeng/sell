package com.imooc.sell.util;

import java.util.List;

import com.imooc.sell.VO.ResultVO;

public class ResultVOUtil {
	
	@SuppressWarnings("unchecked")
	public static ResultVO success(Object object) {
		
		ResultVO<Object>  resultVO = new ResultVO<>();
		resultVO .setCode(1);
		resultVO .setMsg("success!");
		resultVO.setData((List<Object>) object);
		return resultVO;
		
	}
	
public static ResultVO<Object> success() {
		
		ResultVO<Object>  resultVO = new ResultVO<>();
		resultVO .setCode(1);
		resultVO .setMsg("success!");
		resultVO.setData(null);
		return resultVO;
		
	}

public static ResultVO<Object> error(Integer ID , String msg) {
	
	ResultVO<Object>  resultVO = new ResultVO<>();
	resultVO .setCode(ID);
	resultVO .setMsg(msg);
	resultVO.setData(null);
	return resultVO;
	
}


	
	
	

}
