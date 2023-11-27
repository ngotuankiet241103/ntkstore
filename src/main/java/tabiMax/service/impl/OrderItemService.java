package tabiMax.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tabiMax.dto.CartItemDTO;
import tabiMax.dto.OrderItemDTO;
import tabiMax.entity.OrderItemEntity;
import tabiMax.repository.IOrderItemRepository;
import tabiMax.service.IOrderItemService;
@Service
public class OrderItemService implements IOrderItemService{
	@Autowired
	private IOrderItemRepository orderItemRepository;
	
	@Override
	public OrderItemEntity findById(Long id) {
		return orderItemRepository.findById(id);
	}

	@Override
	public Object updateStatusReview(long id, OrderItemDTO orderItemDTO) {
		orderItemRepository.updateStatusReview(id,orderItemDTO.isReview());
		return id;
	}

	

}
