package com.imooc.sell.controller;

import com.imooc.sell.SellApplication;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

/*
 * Description
 *@author Ruimeng
 *@Date 2018/6/8 15:33
 */
@RunWith(SpringRunner.class)
//@SpringApplicationConfiguration(classes = MockServletContext.class)//这个测试单个controller，不建议使用
@SpringApplicationConfiguration(classes = SellApplication.class)//这里的Application是springboot的启动类名。
@WebAppConfiguration
class BuyerOrderControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        //       mvc = MockMvcBuilders.standaloneSetup(new TestController()).build();
        mvc = MockMvcBuilders.webAppContextSetup(context).build();//建议使用这种
    }
    @Test
    public void create() {
    }

    @Test
    public void list() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/data/getMarkers")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("lat", "123.123").param("lon", "456.456")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));


    }

    @Test
    public void detail() {
    }

    @Test
    public void cancel() {
    }
}