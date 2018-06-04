package com.imooc.sell.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CarDTO;
import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.repository.ProductInfoRepository;
import com.imooc.sell.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductInfoRepository repository ;

	@Override
	public ProductInfo findOne(String productId) {
		// TODO Auto-generated method stub
		return repository.findOne(productId);
	}

	@Override
	public List<ProductInfo> findAll() {
		// TODO Auto-generated method stub
		return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
	}

	@Override
	public Page<ProductInfo> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return repository.findAll(pageable);
	}

	@Override
	public ProductInfo save(ProductInfo productInfo) {
		// TODO Auto-generated method stub
		return repository.save(productInfo);
	}

	@Override
	public void increaseStock(List<CarDTO> listCarDTO) {
		for (CarDTO carDTO : listCarDTO) {
			ProductInfo productInfo = repository.findOne(carDTO.getPrductId());
			if(productInfo==null) {
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			Integer result =productInfo.getProductStock()+carDTO.getProductQuantity();
			
			productInfo.setProductStock(result);
			repository.save(productInfo);
	       }
	}
	@Override
	@Transactional
	public void decreaseStock(List<CarDTO> listCarDTO) {
		for (CarDTO carDTO : listCarDTO) {
			ProductInfo productInfo = repository.findOne(carDTO.getPrductId());
			if(productInfo==null) {
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			
			Integer result =productInfo.getProductStock()-carDTO.getProductQuantity();
			if(result<0) {
				throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
			}
			productInfo.setProductStock(result);
			repository.save(productInfo);
		}
		
	}

}
