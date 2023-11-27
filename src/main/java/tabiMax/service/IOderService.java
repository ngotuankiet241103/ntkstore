package tabiMax.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import tabiMax.dto.CancelOrderDTO;
import tabiMax.dto.CartItemDTO;
import tabiMax.dto.OrderDTO;
import tabiMax.dto.RefundOrderDTO;
import tabiMax.entity.OrderEntity;
import tabiMax.entity.OrderItemEntity;

public interface IOderService {

	OrderEntity save(OrderEntity orderEntity);
//	int updateOrder(OrderEntity order);
	void saveAllOrderItem(List<OrderItemEntity> orderItems);
	void updateOrderTotal(OrderEntity orderEntity);
	OrderDTO findByCode(String code);
	void updateTotalAndStatus(OrderEntity orderEntity);
	void removeOrder(OrderEntity orderEntity);
	Page<OrderEntity> findAll(Pageable pageable);
	Map<String, Object> findByUserId(Long userId, Pageable pageable);
	Map<String, Object> findByUserIdAndStatus(Long userId, String common_handle, Pageable pageable);
	Map<String, Object> findByUserIdAndDetailsStatus(Long userId, String orderStatus, Pageable pageable);
	OrderEntity findById(Long id);
	OrderEntity updateAllStatusOrder(OrderDTO order, Long id);
	void updateProduct(String code);
	Page<OrderEntity> findByFullName(String name, Pageable pageable);
	Page<OrderEntity> findByFullNameAndStatus(String name, String orderStatus, Pageable pageable);
	Page<OrderEntity> findByStatus(String orderStatus, Pageable pageable);
	void updateStatusProduct(Long id);
	OrderEntity findOrderByCode(String code);
	void cancelOrder(CancelOrderDTO cancelOrderDTO);
	void refundOrder(RefundOrderDTO refundOrderDTO);
	
}
