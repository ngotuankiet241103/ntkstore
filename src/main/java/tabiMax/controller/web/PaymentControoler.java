package tabiMax.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tabiMax.contraint.PaymentStatus;
import tabiMax.service.IOderService;

@Controller
public class PaymentControoler {
	@Autowired
	private IOderService orderService;
	@GetMapping("/payment")
	public String pageAfterOrder(@RequestParam("orderStatus") String orderStatus,
			@RequestParam("paymentStatus") String paymentStatus,Model model) {
		if(orderStatus.equals(PaymentStatus.orderSuccess)) {
			
			if(paymentStatus.equals(PaymentStatus.payementSuccess)) {
				
				model.addAttribute("paymentStatus", paymentStatus);
			}
			model.addAttribute("orderStatus", orderStatus);
		}
		else if(orderStatus.equals(PaymentStatus.orderFail)) {
			model.addAttribute("paymentStatus", paymentStatus);
		}

		return "web/payment";
	}
}
