package tabiMax.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import tabiMax.entity.OrderItemEntity;

public interface IOrderItemRepository extends CrudRepository<OrderItemEntity, Long> {
	OrderItemEntity save(OrderItemEntity entities);

	Set<OrderItemEntity> findByOrderId(Long id);

	OrderItemEntity findById(Long id);
}
