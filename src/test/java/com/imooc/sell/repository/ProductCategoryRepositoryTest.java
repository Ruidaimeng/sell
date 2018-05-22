package com.imooc.sell.repository;

import com.imooc.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository ;

    @Test
    public void findOneTest(){
       ProductCategory p =repository.findOne(1);
        //使用getOne方法，就会报懒加载异常
        //ProductCategory p =repository.getOne(1);
        System.out.println(p.toString());
    }

    @Test
    public void addOne(){
        ProductCategory p =new ProductCategory ();
        p.setCategoryName("不得了哦了");
        p.setCategoryType(3);
        repository.save(p);

    }

    @Test
    @Transactional  //测试事务，不对数据库进行操作，增加和删除。
    public void  findList(){

        List<Integer> categoryTypeList = Arrays.asList(1,3,10);
        List<ProductCategory> result= repository.findByCategoryTypeIn(categoryTypeList);
       // Assert.assertNotNull(result);
        Assert.assertNotEquals(0,3);
    }


}