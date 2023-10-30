package tabiMax.controller.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import tabiMax.contraint.MaxPageItem;
import tabiMax.entity.OrderEntity;
import tabiMax.paging.pageRequest;
import tabiMax.service.IOderService;

@Controller("apiOrder")
public class OrderController {
	@Autowired
	private IOderService orderService;

	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping("/api/order/user/{userId}")
	public ResponseEntity<Map<String, Object>> getAllOrder(@PathVariable("userId") Long userId,
			@RequestParam(required = false) String orderStatus, @ModelAttribute("paging") pageRequest paging) {
		Pageable pageable = null;
		if (paging.getPage() != null) {
			pageable = new PageRequest(paging.getPage(), MaxPageItem.maxPageItem_product);
		} else if (paging.getPage() == null) {
			pageable = new PageRequest(MaxPageItem.pageStart, MaxPageItem.maxPageItem_product);
		}
		Map<String, Object> response = new HashMap<>();
		if (orderStatus == null) {
			response = orderService.findByUserId(userId, pageable);

		} else if (orderStatus != null) {
			response = orderService.findByUserIdAndStatus(userId, orderStatus, pageable);
		}

		return ResponseEntity.ok(response);
	}

	@GetMapping("/api/order/user/{userId}/cancel")
	public ResponseEntity<Map<String, Object>> getOrderCancel(@PathVariable("userId") Long userId,
			@RequestParam(required = false) String orderStatus, @ModelAttribute("paging") pageRequest paging) {
		Pageable pageable = null;
		if (paging.getPage() != null) {
			pageable = new PageRequest(paging.getPage(), MaxPageItem.maxPageItem_product);
		} else if (paging.getPage() == null) {
			pageable = new PageRequest(MaxPageItem.pageStart, MaxPageItem.maxPageItem_product);
		}
		Map<String, Object> response = new HashMap<>();
		if (orderStatus == null) {
			response = orderService.findByUserId(userId, pageable);
		} else if (orderStatus != null) {
			response = orderService.findByUserIdAndDetailsStatus(userId, orderStatus, pageable);
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping("/api/order/user/{userId}/refund")
	public ResponseEntity<Map<String, Object>> getOrderRefund(@PathVariable("userId") Long userId,
			@RequestParam(required = false) String orderStatus, @ModelAttribute("paging") pageRequest paging) {
		Pageable pageable = null;
		if (paging.getPage() != null) {
			pageable = new PageRequest(paging.getPage(), MaxPageItem.maxPageItem_product);
		} else if (paging.getPage() == null) {
			pageable = new PageRequest(MaxPageItem.pageStart, MaxPageItem.maxPageItem_product);
		}

		Map<String, Object> response = new HashMap<>();
		if (orderStatus == null) {
			response = orderService.findByUserId(userId, pageable);

		} else if (orderStatus != null) {
			response = orderService.findByUserIdAndDetailsStatus(userId, orderStatus, pageable);
		}

		return ResponseEntity.ok(response);
	}
}
