package tabiMax.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import tabiMax.dto.DetailsOrderDTO;
import tabiMax.dto.OrderDTO;
import tabiMax.entity.OrderEntity;
import tabiMax.entity.OrderItemEntity;
import tabiMax.entity.ProductEntity;
import tabiMax.modelMapper.modelMapper;
import tabiMax.paging.pageRequest;
import tabiMax.repository.IOrderItemRepository;
import tabiMax.repository.IOrderRepository;
import tabiMax.repository.IProductRepository;
import tabiMax.service.IOderService;

@Service
public class OrderService implements IOderService {
	@Autowired
	private IOrderRepository orderRepository;
	@Autowired
	private IOrderItemRepository orderItemRepository;
	@Autowired
	private IProductRepository productRepository;

	@Override
	public OrderEntity save(OrderEntity orderEntity) {
		return orderRepository.save(orderEntity);

	}
//	@Override
//	public int updateOrder(OrderEntity order) {
//		
//		return orderRepository.updateOrderEntityOrderItem(order.getOrderItems(), order.getId());
//	}

	@Override
	public void saveAllOrderItem(List<OrderItemEntity> orderItems) {
		for (OrderItemEntity o : orderItems) {
			orderItemRepository.save(o);
		}
	}

	@Transactional
	@Override
	public void updateOrderTotal(OrderEntity orderEntity) {
		orderRepository.updateOrderEntityTotal(orderEntity.getTotal(), orderEntity.getId());

	}

	@Override
	public OrderDTO findByCode(String code) {
		return orderRepository.findByCode(code).map(order -> {
			OrderDTO orderDTO = modelMapper.toMapper().map(order, OrderDTO.class);
			orderDTO.setDetailsOrder(modelMapper.toMapper().map(order.getDetailsOrder(), DetailsOrderDTO.class));
			return orderDTO;
		}).orElseThrow(() -> new RuntimeException("order not found"));
	}

	@Transactional
	@Override
	public void updateTotalAndStatus(OrderEntity orderEntity) {
		orderRepository.updateTotalAndStatusl(orderEntity.getTotal(), orderEntity.getPaymentStatus(),
				orderEntity.getId());

	}

	@Transactional
	@Override
	public void removeOrder(OrderEntity orderEntity) {
		orderRepository.delete(orderEntity);

	}

	@Override
	public Page<OrderEntity> findAll(Pageable pageable) {

		return orderRepository.findAll(pageable);
	}

	@Override
	public Map<String, Object> findByUserId(Long userId, Pageable pageable) {
		List<OrderDTO> listOrder = null;

		Page<OrderEntity> pageOrder = orderRepository.findByUserId(userId, pageable);
		Map<String, Object> response = new HashMap<>();
		listOrder = pageOrder.getContent().stream().map(order -> modelMapper.toMapper().map(order, OrderDTO.class))
				.collect(Collectors.toList());
		pageRequest paging = new pageRequest(pageOrder.getNumber(), pageOrder.getTotalPages());
		response.put("orders", listOrder);
		response.put("paginartion", paging);

		return response;
	}

	@Override
	public Map<String, Object> findByUserIdAndStatus(Long userId, String common_handle, Pageable pageable) {

		List<OrderDTO> listOrder = null;

		Page<OrderEntity> pageOrder = orderRepository.findByUserIdAndStatus(userId, common_handle, pageable);
		Map<String, Object> response = new HashMap<>();
		listOrder = pageOrder.getContent().stream().map(order -> modelMapper.toMapper().map(order, OrderDTO.class))
				.collect(Collectors.toList());
		pageRequest paging = new pageRequest(pageOrder.getNumber(), pageOrder.getTotalPages());

		response.put("orders", listOrder);
		response.put("paginartion", paging);

		return response;

	}

	@Override
	public Map<String, Object> findByUserIdAndDetailsStatus(Long userId, String orderStatus, Pageable pageable) {

		List<OrderDTO> listOrder = null;

		Page<OrderEntity> pageOrder = orderRepository.findByUserIdAndDetailStatus(userId, orderStatus, pageable);
		Map<String, Object> response = new HashMap<>();
		listOrder = pageOrder.getContent().stream().map(order -> modelMapper.toMapper().map(order, OrderDTO.class))
				.collect(Collectors.toList());
		pageRequest paging = new pageRequest(pageOrder.getNumber(), pageOrder.getTotalPages());

		response.put("orders", listOrder);
		response.put("paginartion", paging);

		return response;

	}

	@Override
	public OrderEntity findById(Long id) {

		return orderRepository.findById(id);
	}

	@Transactional
	@Override
	public OrderEntity updateAllStatusOrder(OrderDTO order, Long id) {
		OrderEntity orderEntity = modelMapper.toMapper().map(order, OrderEntity.class);
		orderRepository.updateAllStatus(orderEntity.getStatus(), orderEntity.getDetailStatus(), id);
		return orderRepository.findById(id);
	}

	@Transactional
	@Override
	@Async
	public void updateProduct(String code) {
		OrderEntity orderEntity = orderRepository.findByCode(code).orElseThrow(() -> new RuntimeException("order not found"));
		for (OrderItemEntity o : orderEntity.getOrderItems()) {
			ProductEntity product = productRepository.findById(o.getProduct().getId());
			Integer quanlity = product.getSizeQuantityMap().get(o.getSize());
			Integer newQuanlity = quanlity - o.getQuantity();
			System.out.println(product.getId());
			product.getSizeQuantityMap().replace(o.getSize(), quanlity, newQuanlity);
			if (newQuanlity == 0) {
				updateStatusProduct(product.getId());
			}
			productRepository.updateSizeQuantity(o.getSize(), newQuanlity, product.getId());
		}

	}

	@Transactional
	@Override
	@Async
	public void updateStatusProduct(Long id) {
		ProductEntity product = productRepository.findById(id);
		int total = 0;
		for (Integer value : product.getSizeQuantityMap().values()) {
			if (value == 0) {
				total++;
			}
		}
		if (total == product.getSizeQuantityMap().size()) {
			productRepository.updateStatus(0, product.getId());
		}
	}

	@Override
	public Page<OrderEntity> findByFullName(String name, Pageable pageable) {
		return orderRepository.findByUserFullName(name, pageable);
	}

	@Override
	public Page<OrderEntity> findByFullNameAndStatus(String name, String orderStatus, Pageable pageable) {

		return orderRepository.findByUserFullNameAndStatus(name, orderStatus, pageable);
	}

	@Override
	public Page<OrderEntity> findByStatus(String orderStatus, Pageable pageable) {

		return orderRepository.findByStatus(orderStatus, pageable);
	}

	@Override
	public OrderEntity findOrderByCode(String code) {
		
		return orderRepository.findByCode(code).orElseThrow(() -> new RuntimeException("order not found"));
	}

}
