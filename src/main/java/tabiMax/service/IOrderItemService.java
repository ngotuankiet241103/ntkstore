package tabiMax.service;

import tabiMax.dto.CartItemDTO;
import tabiMax.dto.OrderItemDTO;
import tabiMax.entity.OrderItemEntity;

public interface IOrderItemService {

	OrderItemEntity findById(Long id);

	Object updateStatusReview(long id, OrderItemDTO orderItemDTO);

}
