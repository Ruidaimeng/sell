package com.imooc.sell.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.couchbase.client.deps.com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.util.serializer.Date2LongSerializer;

/**
* @ClassName: OrderDTO
* @Description: 数据传输类，用于在各层之间传输数据,与订单猪表不同的是，该类包含了订单详情对象
* @author ruimeng
* @date 2018年5月31日 下午6:58:04
*
*/ 
//非空时不进行序列化，不返回到前端页面
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
	
	 

	/** 订单id. */
    private String orderId;

    /** 买家名字. */
    private String buyerName;

    /** 买家手机号. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信Openid. */
    private String buyerOpenid;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单. */
    private Integer orderStatus;

    /** 支付状态, 默认为0未支付. */
    private Integer payStatus;

    /** 创建时间. */
    //加上这个注解，就可以把时间转换为long
  @JsonSerialize(using=Date2LongSerializer.class)
    private Date createTime;

    /** 更新时间. */
  @JsonSerialize(using=Date2LongSerializer.class)
    private Date updateTime;

    List<OrderDetail> orderDetailList;

    public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerPhone() {
		return buyerPhone;
	}

	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	public String getBuyerOpenid() {
		return buyerOpenid;
	}

	public void setBuyerOpenid(String buyerOpenid) {
		this.buyerOpenid = buyerOpenid;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	@Override
	public String toString() {
		return "OrderDTO [orderId=" + orderId + ", buyerName=" + buyerName + ", buyerPhone=" + buyerPhone
				+ ", buyerAddress=" + buyerAddress + ", buyerOpenid=" + buyerOpenid + ", orderAmount=" + orderAmount
				+ ", orderStatus=" + orderStatus + ", payStatus=" + payStatus + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", orderDetailList=" + orderDetailList + "]";
	}
	
	

}
