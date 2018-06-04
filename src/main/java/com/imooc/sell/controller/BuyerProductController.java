package com.imooc.sell.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.sell.VO.ProductInfoVO;
import com.imooc.sell.VO.ProductVO;
import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.service.CategoryService;
import com.imooc.sell.service.ProductService;
import com.imooc.sell.util.ResultVOUtil;



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
	
	@Autowired
	private ProductService productService ;
	
	@Autowired
	private CategoryService categoryService;

	@SuppressWarnings("unchecked")
	@GetMapping("/list")
	public ResultVO<Object> list() {
		
		//1、查询所有的上架商品
		List<ProductInfo> productInfoList = productService.findAll() ;
	
		//2、查询类目（一次性查询所有的类目）  查询出的所有商品，主要都有哪些类目。
		List<Integer> catagoryTypeList = new ArrayList<>();
		
//		//传统方法
//	for (ProductInfo productInfo : productInfoList) {
//		catagoryTypeList.add(productInfo.getCategoryType());
//		}
		
		//精简方法。浪不他表达式lambda表达式。
		catagoryTypeList = productInfoList.stream()
				                          .map(e -> e.getCategoryType())
				                          .collect(Collectors.toList());
		List<ProductCategory> productCategorList =categoryService.findByCategoryTypeIn(catagoryTypeList);
		//3、数据拼装
		List<ProductVO> productVOList = new ArrayList<>();
		
		//遍历所有类目
		for (ProductCategory productCategory : productCategorList) {
			ProductVO productVO = new ProductVO();
			productVO.setCategoryName(productCategory.getCategoryName());
			productVO.setCategoryType(productCategory.getCategoryType());
			List<ProductInfoVO> list = new ArrayList<>();
			//遍历所有商品，匹配类目
			for (ProductInfo productInfo : productInfoList) {
				if(productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
					ProductInfoVO productInfoVO = new ProductInfoVO();
					//不用一个一个字段取值，把infoVO转成VO
					//		productInfoVO.setProductName(productInfo.getProductName());
					//spring提供了一个BeanUtils,可以把一个对象的属性拷贝到另一个对象中去。
					BeanUtils.copyProperties(productInfo, productInfoVO);
					list.add(productInfoVO);
				};
			}
			productVO.setProductInfoVOList(list);
			productVOList.add(productVO);
		}
		
		//封装结果集
//		ResultVO<ProductVO>  resultVO =new ResultVO<ProductVO>();
//		resultVO.setCode(1);
//		resultVO.setMsg("success!");
//		resultVO.setData(productVOList);
		
		return ResultVOUtil.success(productVOList);
		
	}


}
