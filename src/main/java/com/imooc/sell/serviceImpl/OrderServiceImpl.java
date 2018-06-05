package com.imooc.sell.serviceImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.imooc.sell.converter.OrderMaster2OrderDTO;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dataobject.OrderMaster;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CarDTO;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.repository.OrderDetailRepository;
import com.imooc.sell.repository.OrderMasterRepository;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.ProductService;
import com.imooc.sell.util.KeyUtil;


/**
* @ClassName: OrderServiceImpl
* @Description: 订单业务层实现类
* @author ruimeng
* @date 2018年6月2日 上午9:19:25
*
*/ 
@Service
@Transactional //事务
public class OrderServiceImpl implements OrderService {
	
	 private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private ProductService productService;  //需要查询商品信息
	
	@Autowired
	private OrderMasterRepository orderMasterRepository;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Override
	public OrderDTO createOrder(OrderDTO orderDTO) {
		
		BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
		String orderId = KeyUtil.getUniqueKey();

		//1、查询商品的数量，价格 
		List<OrderDetail> orderDetailList =orderDTO.getOrderDetailList() ;
	    List<CarDTO> listCarDTO = new ArrayList();
		
		for (OrderDetail orderDetail : orderDetailList) {
			ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
			if(productInfo==null) {
				//抛出商品不存在的异常
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			//2、计算总价
		
			//页面传来的数据只有，商品ID和商品数量
			//订单详情入库
			BeanUtils.copyProperties(productInfo, orderDetail);//把商品信息拷贝到订单详情信息中去（同时也会拷贝一些相同字段，使得商品状态变为）
			//所以，要重新赋值
			productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
			//注意单价要从商品详情信息中获取（数据库中的），不能从订单信息中去
			orderAmount =orderAmount.add(productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())));
			
			orderDetail.setDetailId(KeyUtil.getUniqueKey());
			orderDetail.setOrderId(orderId);
			orderDetailRepository.save(orderDetail);
			
			CarDTO catdto = new CarDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
			listCarDTO.add(catdto);   //一般做法，遍历订单创建购物车对象
			
		}
		
		//3、写入订单表（OrderMaster）
		
		OrderMaster oederMaster = new OrderMaster();
		orderDTO.setOrderId(orderId);
		BeanUtils.copyProperties(orderDTO, oederMaster);//把商品信息拷贝到订单详情信息中去
		//给orderMaster赋值，要在复制属性值之后。
		oederMaster.setOrderAmount(orderAmount);
		orderMasterRepository.save(oederMaster);
		
		
		//4、扣库存
		
		
		//使用lumbda算法出错！！111++++++++++》》》》》》》》》》》》》》
	//	List<CarDTO> listCarDTO = 
//	orderDTO.getOrderDetailList().stream()
//		   .map(e -> new CatDTO(e.getProductId(),e.getProductQuantity()))
		
		productService.decreaseStock(listCarDTO);
		
		return orderDTO;
	}

	@Override
	public OrderDTO findOneOrder(String orderID) {
		OrderMaster orderMaster = orderMasterRepository.findOne(orderID);
		if(orderMaster ==null) {
			throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
		}
		List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderID);
		
		if(orderDetailList==null) {
			throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
		}
		OrderDTO orderdto = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderdto);
		orderdto.setOrderDetailList(orderDetailList);
		return orderdto;
	}

	@Override
	public Page<OrderDTO> findOrderList(String buyerId, Pageable pageable) {
		
		Page<OrderMaster> orderMaterPage = orderMasterRepository.findByBuyerOpenid(buyerId, pageable);
		List<OrderDTO> orderDTOList = OrderMaster2OrderDTO.converter(orderMaterPage.getContent());
		
		Page<OrderDTO> pageOderDTO = new PageImpl<OrderDTO>(orderDTOList,pageable,orderMaterPage.getTotalElements());
		
		return pageOderDTO ;
	}

	@Override
	public OrderDTO cancelOrder(OrderDTO orderDTO) {
		
		OrderMaster orderMaster = new OrderMaster() ;
		
		//判断订单状态是否可以取消
		if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
			logger.error("【取消订单】 订单状态错误，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		//改变订单状
		orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
		//注意属性什么时候拷贝
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster updateResult=orderMasterRepository.save(orderMaster);
		if(updateResult==null) {
			logger.error("【取消订单】 更新失败，orderId={},orderMaster={}",orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
			
		}
		
		//退库存
		if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
			logger.error("【取消订单】 订单中无商品详情，orderDTO={}",orderDTO);
			throw new SellException(ResultEnum.ORDER_STATUS_EMPTY);
		}

        //4. 扣库存
		List<CarDTO> cartDTOList = new ArrayList();
		for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
			CarDTO cart = new CarDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
			cartDTOList.add(cart);
		}
		
		//lumbda表达式用不了
//        List<CarDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
//                new CartDTO(e.getProductId(), e.getProductQuantity())
//        ).collect(Collectors.toList());
        
        productService.increaseStock(cartDTOList);
        //退款
     // TODO 退款给
        //发送websocket消息
      //  webSocket.sendMessage(orderDTO.getOrderId());

        return orderDTO;
	}

	@Override
	public OrderDTO finishOrder(OrderDTO orderDTO) {
		OrderMaster orderMaster = new OrderMaster() ;
		//判断订单状态
				if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
					logger.error("【完结订单】 订单状态错误，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
					throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
				}
		//修改订单状态
				//注意属性什么时候拷贝
				//注意orderDTO也要更改状态
				orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
				BeanUtils.copyProperties(orderDTO, orderMaster);
				//改变订单状
				orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
				
				OrderMaster orderMasterResult=orderMasterRepository.save(orderMaster);
		if(orderMasterResult==null) {
			logger.error("【完结订单】 订单完结失败，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		       return orderDTO;
	}

	@Override
	@Transactional
	public OrderDTO paidOrder(OrderDTO orderDTO) {
		OrderMaster orderMaster = new OrderMaster() ;
		//判断订单状态
				if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
					logger.error("【订单支付完成】 订单状态错误，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
					throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
				}
				
		//判断支付状态
				if(!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
					logger.error("【订单支付完成】 支付状态错误，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
					throw new SellException(ResultEnum.PAY_STATUS_ERROR);
				}
				
		//修改订单状态
				//注意属性什么时候拷贝
				//注意orderDTO也要更改状态
				orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
				BeanUtils.copyProperties(orderDTO, orderMaster);
				//改变订单状
				orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
				
				OrderMaster orderMasterResult=orderMasterRepository.save(orderMaster);
		if(orderMasterResult==null) {
			logger.error("【支付订单】 订单支付完结失败，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.PAY_UPDATE_FAIL);
		}
		       return orderDTO;
	}

}
