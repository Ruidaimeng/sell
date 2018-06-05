package com.imooc.sell.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.OrderForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class OrderForm2OrderDTOConverter {

    public static  Logger logger = LoggerFactory.getLogger(OrderForm2OrderDTOConverter.class);
    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress(orderForm.getAdress());
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerPhone(orderForm.getPhone());
      //  orderDTO.s
        // 把json字符串转换成List
        List<OrderDetail> list = new ArrayList<>();
        try {
           list = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e) {
            logger.error("【对象转换出错】 参数不正确 ，string={}",orderForm.getItems());
            throw new SellException(ResultEnum.FORM_RARAM_ERROOR);

        }

        orderDTO.setOrderDetailList(list);
        return orderDTO ;
    }
}
