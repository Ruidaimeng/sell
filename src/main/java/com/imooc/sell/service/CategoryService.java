package com.imooc.sell.service;

import com.imooc.sell.dataobject.ProductCategory;

import java.util.List;
import java.util.Locale;

public interface CategoryService {

     ProductCategory findOne(Integer  catageoryId);

     List<ProductCategory> findAll() ;

    List<ProductCategory> findByCategoryTypeIn(List<Integer> catageoryTpyeList);

    ProductCategory save(ProductCategory productCategory);

}
