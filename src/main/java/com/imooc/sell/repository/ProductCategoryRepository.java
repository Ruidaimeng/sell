package com.imooc.sell.repository;

import com.imooc.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    public List<ProductCategory>  findByCategoryTypeIn (List<Integer> categoryTypeList) ;
}
