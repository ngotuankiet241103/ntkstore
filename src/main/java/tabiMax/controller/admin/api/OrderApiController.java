package tabiMax.controller.admin.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import tabiMax.composite.OrderRequestUpdate;
import tabiMax.contraint.MaxPageItem;
import tabiMax.contraint.OrderStatus;
import tabiMax.dto.OrderDTO;
import tabiMax.entity.OrderEntity;
import tabiMax.modelMapper.modelMapper;
import tabiMax.paging.pageRequest;
import tabiMax.service.IOderService;
import tabiMax.service.IUserService;

@Controller
public class OrderApiController {
	@Autowired
	private IOderService orderService;
	@Autowired
	private IUserService userService;
	@GetMapping("/api/admin/order/status")
	public ResponseEntity<List<String>> getOrderStatus() {
		return ResponseEntity.ok(OrderStatus.getStatusAvailble());
	}

	@GetMapping("/api/admin/order")
	public ResponseEntity<Map<String, Object>> getAllProduct(@ModelAttribute("paging") pageRequest paging,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "sortName",defaultValue = "") String sortName,
			@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
			@RequestParam(value = "orderStatus", defaultValue = "") String orderStatus
			) {
		Pageable pageable = null;
		Sort sort = null;
		if(!sortName.equals("")) {
			sort = sortBy.equals("DESC") ?  new Sort(sortName) : new Sort(Sort.Direction.DESC, sortName);
		}
		if (paging.getPage() != null) {
			pageable =  new PageRequest(paging.getPage(), MaxPageItem.maxPageItem_product,sort);
		} else if (paging.getPage() == null) {
			pageable =  new PageRequest(MaxPageItem.pageStart, MaxPageItem.maxPageItem_product,sort);
		}
		List<OrderEntity> listOrder = null;
		Page<OrderEntity> pageOrder = null;
		if(!name.equals("") && orderStatus.equals("")) {
			pageOrder =  orderService.findByFullName(name,pageable);
		}
		else if(!name.equals("") && !orderStatus.equals("")){
			pageOrder = orderService.findByFullNameAndStatus(name,orderStatus,pageable);
		}
		else if(name.equals("") && !orderStatus.equals("")){
			pageOrder = orderService.findByStatus(orderStatus,pageable);
		}
		else if(name.equals("")){
			pageOrder = orderService.findAll(pageable);
		}
		
		Map<String, Object> response = new HashMap<>();
		listOrder = pageOrder.getContent();
		paging.setTotalsPage(pageOrder.getTotalPages());
		paging.setPage(pageOrder.getNumber());
		response.put("orders", listOrder);
		response.put("paginartion", paging);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/api/admin/order/{id}")
	public ResponseEntity<Map<String, Object>> getOrderById(@PathVariable("id") Long id) {
		OrderEntity orderDetail = orderService.findById(id);
		Map<String, Object> response = new HashMap<>();
		response.put("order", orderDetail);
		Map<String, Object> objectStatus = new HashMap<>();
		objectStatus.put("status", OrderStatus.getStatus());
		objectStatus.put("detailsStatus", OrderStatus.getDetailStatus());
		response.put("orderStatus", objectStatus);
		return ResponseEntity.ok(response);
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PutMapping("/api/admin/order/{id}")
	public ResponseEntity<?> updateOrder(@RequestBody OrderRequestUpdate orderRequest, @PathVariable("id") Long id) {
		OrderDTO order = modelMapper.toMapper().map(orderRequest, OrderDTO.class);
		OrderEntity orderEntity = orderService.updateAllStatusOrder(order, id);
		if (orderEntity == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(orderEntity);
	}
}
