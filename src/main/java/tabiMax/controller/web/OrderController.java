package tabiMax.controller.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tabiMax.dto.CancelOrderDTO;
import tabiMax.dto.RefundOrderDTO;
import tabiMax.dto.ReviewDTO;
import tabiMax.service.FileUpload;
import tabiMax.service.IOderService;
import tabiMax.service.IOrderItemService;
import tabiMax.service.IReviewService;
import tabiMax.utils.tranfromListToMap;

@Controller("webOrderController")
public class OrderController {
	@Autowired
	private IOderService orderService;
	@Autowired
	private IOrderItemService orderItemService;
	@Autowired
	private IReviewService reviewService;
	@Autowired
	private FileUpload fileUpload;
	@GetMapping("/customer/order")
	public String getPageOrder() {
		return "web/orderDetails";
	}
	@GetMapping("/customer/order/view")
	public String getDetailsOrder(@RequestParam("code") String code,Model model) {
		model.addAttribute("order", orderService.findByCode(code));
		return "web/detailsOrder";
	}
	@GetMapping("/customer/order/view/cancel")
	public String getPageCancelOrder(@RequestParam("code") String code,Model model) {
		model.addAttribute("order", orderService.findByCode(code));
		return "web/cancelOrder";
	}
	@GetMapping("/customer/order/view/refund")
	public String getPageRefundOrder(@RequestParam("code") String code,Model model) {
		model.addAttribute("order", orderService.findByCode(code));
		return "web/refundOrder";
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
	@PostMapping("/customer/order/view/cancel")
	public String addCancelOrder(@ModelAttribute("order") CancelOrderDTO cancelOrderDTO,Model model) {
		orderService.cancelOrder(cancelOrderDTO);
		return "redirect:/customer/order";
	}
	@PostMapping("/customer/order/view/refund")
	public String addRefundOrder(@ModelAttribute("refundOrderDTO") RefundOrderDTO refundOrderDTO,Model model) {
		try {
			if (refundOrderDTO.getFileImage() != null) {
				String url = fileUpload.uploadFile(refundOrderDTO.getFileImage());
				refundOrderDTO.setImage(url);
			}
			orderService.refundOrder(refundOrderDTO);
			return "redirect:/customer/order";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "web/orderDetails";
	}
}
