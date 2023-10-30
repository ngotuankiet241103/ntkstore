package tabiMax.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import tabiMax.entity.OrderEntity;

public interface IOrderRepository extends Repository<OrderEntity, Long> {
	OrderEntity save(OrderEntity orderEntity);

	@Modifying
	@Query("UPDATE OrderEntity c SET c.total= ?1 WHERE c.id = ?2")
	void updateOrderEntityTotal(float total, Long id);

	Optional<OrderEntity> findByCode(String code);

	@Modifying
	@Query("UPDATE OrderEntity c SET c.total= ?1, c.paymentStatus = ?2 WHERE c.id = ?3")
	void updateTotalAndStatusl(float total, String paymentStatus, Long id);

	void delete(OrderEntity orderEntity);

	Page<OrderEntity> findAll(Pageable pageable);

	Page<OrderEntity> findByUserId(Long userId, Pageable pageable);

	Page<OrderEntity> findByUserIdAndStatus(Long userId, String common_handle, Pageable pageable);

	Page<OrderEntity> findByUserIdAndDetailStatus(Long userId, String orderStatus, Pageable pageable);

	OrderEntity findById(Long id);

	@Modifying
	@Query("UPDATE OrderEntity c SET c.status= ?1, c.detailStatus = ?2 WHERE c.id = ?3")
	void updateAllStatus(String status, String detailStatus, Long id);

	Page<OrderEntity> findByUserFullName(String name, Pageable pageable);

	Page<OrderEntity> findByUserFullNameAndStatus(String name, String orderStatus, Pageable pageable);

	Page<OrderEntity> findByStatus(String orderStatus, Pageable pageable);
}
