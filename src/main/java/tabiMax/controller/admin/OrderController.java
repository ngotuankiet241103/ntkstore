package tabiMax.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import tabiMax.contraint.MaxPageItem;
import tabiMax.entity.OrderEntity;
import tabiMax.paging.pageRequest;
import tabiMax.service.IOderService;

@Controller
@RequestMapping("/admin")
public class OrderController {
	@Autowired
	private IOderService orderService;
	
//	public ResponseEntity<List<OrderEntity>> pageOrder(Model model,@ModelAttribute("paging") pageRequest paging) {
//		Pageable pageable = null;
//		pageable = new PageRequest(MaxPageItem.pageStart, MaxPageItem.maxPageItem_product);
//		if (paging.getMaxPageItem() != null) {
//			pageable = new PageRequest(paging.getPage(), paging.getMaxPageItem());
//		}
//		
//		return ResponseEntity.ok(orderService.findAll(pageable).getContent());
//	}
	@GetMapping("/order")
	public String pageOrder() {
		return "admin/order";
	}
	
}
