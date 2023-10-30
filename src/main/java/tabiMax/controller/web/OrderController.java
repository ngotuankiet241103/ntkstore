package tabiMax.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tabiMax.dto.ReviewDTO;
import tabiMax.service.IOderService;
import tabiMax.service.IOrderItemService;
import tabiMax.service.IReviewService;

@Controller("webOrderController")
public class OrderController {
	@Autowired
	private IOderService orderService;
	@Autowired
	private IOrderItemService orderItemService;
	@Autowired
	private IReviewService reviewService;
	@GetMapping("/customer/order")
	public String getPageOrder() {
		return "web/orderDetails";
	}
	@GetMapping("/customer/order/view")
	public String getDetailsOrder(@RequestParam("code") String code,Model model) {
		model.addAttribute("order", orderService.findByCode(code));
		return "web/detailsOrder";
	}
	@GetMapping("/customer/order/view/review")
	public String getPageReview(@RequestParam("orderItemId") Long id,Model model) {
		model.addAttribute("orderItem", orderItemService.findById(id));
		return "web/reviewOrder";
	}
	@PostMapping("/customer/order/view/review")
	public String addComment(@ModelAttribute("reviewDTO") ReviewDTO review) {
		reviewService.saveComment(review);
		return "web/orderDetails";
	}
}
