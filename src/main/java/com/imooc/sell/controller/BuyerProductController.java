package com.imooc.sell.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.dataobject.ProductInfo;

/**
* @ClassName: BuyerProductController
* @Description: 买家商品
* @author ruimeng
* @date 2018年5月23日 下午8:38:30
*
*/ 
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
	
	@GetMapping("/list")
	public ResultVO<ProductInfo> list() {
		
		ResultVO<ProductInfo>  r =new ResultVO<ProductInfo> ();
		
		return r;
		
	}
//	@GetMapping("/list")
//	public String list() {
//		
//		ResultVO<ProductInfo>  r =new ResultVO<ProductInfo> ();
//		
//		return "hello";
//		
//	}

}
