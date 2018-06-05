package com.imooc.sell.util.serializer;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


/**
* @ClassName: Date2LongSerializer
* @Description: 将日期转换为long类型,
* @author ruimeng
* @date 2018年6月5日 下午7:40:04
*
*/ 
public class Date2LongSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator , SerializerProvider serializerProvider)
			throws IOException, JsonProcessingException {
		jsonGenerator.writeNumber(date.getTime()/1000) ;
		
	}

}
