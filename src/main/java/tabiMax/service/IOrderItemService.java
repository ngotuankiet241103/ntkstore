package tabiMax.service;

import tabiMax.entity.OrderItemEntity;

public interface IOrderItemService {

	OrderItemEntity findById(Long id);

}
