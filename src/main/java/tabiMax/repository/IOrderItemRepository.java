package tabiMax.repository;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tabiMax.entity.OrderItemEntity;

public interface IOrderItemRepository extends CrudRepository<OrderItemEntity, Long> {
	OrderItemEntity save(OrderItemEntity entities);

	Set<OrderItemEntity> findByOrderId(Long id);

	OrderItemEntity findById(Long id);
	@Transactional
	@Modifying
	@Query("UPDATE OrderItemEntity c SET c.isReview = ? 2 WHERE c.id = ?1")
	void updateStatusReview(long id, boolean review);
}
