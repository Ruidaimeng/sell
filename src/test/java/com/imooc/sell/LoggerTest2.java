package com.imooc.sell;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 日志测试类2
 *
 * @author ruimeng
 * @create 2018-05-19 9:04
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerTest2 {

    private Logger logger = LoggerFactory.getLogger(LoggerTest2.class);
    @Test
    public void  test1(){
        logger.error("error..........");
        logger.info(" info .......");
        logger.debug(" debug........");
    }

}
