package tabiMax.controller.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tabiMax.composite.OrderComposite;
import tabiMax.contraint.OrderStatus;
import tabiMax.contraint.PaymentStatus;
import tabiMax.dto.CartItemDTO;
import tabiMax.dto.OrderItemDTO;
import tabiMax.dto.OrderItemRequest;
import tabiMax.dto.payment;
import tabiMax.entity.DetailsOrder;
import tabiMax.entity.OrderEntity;
import tabiMax.entity.OrderItemEntity;
import tabiMax.modelMapper.modelMapper;
import tabiMax.payment.Config;
import tabiMax.service.ICartService;
import tabiMax.service.IOderService;
import tabiMax.service.IProductService;
import tabiMax.service.IUserService;

@Controller
public class CheckoutController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IProductService productService;
	@Autowired
	private IOderService orderService;
	@Autowired
	private ICartService cartService;
	@GetMapping("product/detail/checkout")
	public String pageCheckoutCart(@RequestParam("cartId") List<Long> ids,Model model) {
//		List<CartItemDTO> orders = cartService.findByIds(ids);
//		System.out.println(ids);
		model.addAttribute("orderItems", cartService.findByIds(ids));
		return "web/checkout";
	}
	@PostMapping("product/detail/checkout")
	public String pageCheckout(@ModelAttribute("orderItem") OrderItemRequest order, Model model) {
		OrderItemDTO orderitem = modelMapper.toMapper().map(order, OrderItemDTO.class);
		List<OrderItemDTO> orders = new ArrayList<>();
		orderitem.setProduct(productService.findById(orderitem.getProductId()));
		orders.add(orderitem);
		model.addAttribute("orderItems", orders);
		
		return "web/checkout";
	}
	
	@GetMapping("checkout/payment")
	public String handlePayment(@RequestParam("vnp_Amount") Float amount, @RequestParam("vnp_TxnRef") String code,
			@RequestParam("vnp_TransactionStatus") String status, RedirectAttributes redirectAttributes) {
		OrderEntity orderEntity = orderService.findOrderByCode(code);
		String orderStatus = "";
		String paymentStatus = "";
		if (status.equals("00")) {
			orderService.updateProduct(code);
			orderEntity.setTotal(amount / 100);
			orderEntity.setPaymentStatus(OrderStatus.paymentStatus_paid);
			orderService.updateTotalAndStatus(orderEntity);
			orderStatus = PaymentStatus.orderSuccess;
			paymentStatus = PaymentStatus.payementSuccess;
		} else {
			orderStatus = PaymentStatus.orderFail;
			paymentStatus = PaymentStatus.paymentFail;
			orderService.removeOrder(orderEntity);
		}
		redirectAttributes.addAttribute("orderStatus", orderStatus);
		redirectAttributes.addAttribute("paymentStatus", paymentStatus);
		return "redirect:/payment";
	}

	@PostMapping("checkout/cod")
	public ResponseEntity<Map<String,String>> payCod(@RequestBody OrderComposite order,
			RedirectAttributes redirectAttributes) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		DetailsOrder detailsOrder = modelMapper.toMapper().map(order, DetailsOrder.class);
		List<OrderItemEntity> orderItems = new ArrayList<>();
		String code = Config.getRandomNumber(8);
		OrderEntity orderEntity = new OrderEntity(code, OrderStatus.common_handle,
				OrderStatus.details_handle, OrderStatus.paymentStatus_pending, OrderStatus.paymentMethod_cod,
				detailsOrder);
		orderEntity.setUser(userService.findByEmail(userName));
		OrderEntity orderEntityNow = orderService.save(orderEntity);
		float total = 0;

		for (int i = 0; i < order.getTotalPrice().size(); i++) {
			total += order.getTotalPrice().get(i);
			OrderItemEntity orderItemEntity = new OrderItemEntity();
			orderItemEntity.setTotalPrice(order.getTotalPrice().get(i));
			orderItemEntity.setQuantity(order.getQuantity().get(i));
			orderItemEntity.setSize(order.getSize().get(i));
			orderItemEntity.setOrder(orderEntityNow);
			orderItemEntity.setProduct(productService.findById(order.getProductId().get(i)));
			orderItems.add(orderItemEntity);
		}
		orderEntityNow.setTotal(total);
		orderService.updateOrderTotal(orderEntity);
		orderService.saveAllOrderItem(orderItems);
		orderService.updateProduct(code);
		String orderStatus = PaymentStatus.orderSuccess;
		String url = "payment?orderStatus="+orderStatus + "&paymentStatus=Chưa thanh toán";
		Map<String,String> response = new HashMap<>();
		response.put("url", url);
		return ResponseEntity.ok(response);
	}

	@PostMapping("checkout")
	public ResponseEntity<Map<String,String>> createPayment(@RequestBody OrderComposite order)
			throws UnsupportedEncodingException {

		Pageable pageable = new PageRequest(0, 10);
		String vnp_TxnRef = Config.getRandomNumber(8);
		String vnp_IpAddr = Config.IPDEFAULT;
		String vnp_TmnCode = Config.vnp_TmnCode;

		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		DetailsOrder detailsOrder = modelMapper.toMapper().map(order, DetailsOrder.class);
		List<OrderItemEntity> orderItems = new ArrayList<>();
		OrderEntity orderEntity = new OrderEntity(vnp_TxnRef, OrderStatus.common_handle, OrderStatus.details_handle,
				OrderStatus.paymentStatus_pending, OrderStatus.paymentMethod_credit, detailsOrder);
		orderEntity.setUser(userService.findByEmail(userName));
		OrderEntity orderEntityNow = orderService.save(orderEntity);
		float total = 0;
		for (int i = 0; i < order.getTotalPrice().size(); i++) {
			total += order.getTotalPrice().get(i);
			OrderItemEntity orderItemEntity = new OrderItemEntity();
			orderItemEntity.setTotalPrice(order.getTotalPrice().get(i));
			orderItemEntity.setQuantity(order.getQuantity().get(i));
			orderItemEntity.setSize(order.getSize().get(i));
			orderItemEntity.setOrder(orderEntityNow);
			orderItemEntity.setProduct(productService.findById(order.getProductId().get(i)));
			orderItems.add(orderItemEntity);
		}
		orderService.saveAllOrderItem(orderItems);
		long amount = (long) (total * 100);
		Map<String, String> vnp_Params = new HashMap<>();
		vnp_Params.put("vnp_Version", Config.vnp_Version);
		vnp_Params.put("vnp_Command", Config.vnp_Command);
		vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
		vnp_Params.put("vnp_Amount", String.valueOf(amount));
		vnp_Params.put("vnp_CurrCode", "VND");
		vnp_Params.put("vnp_BankCode", "NCB");
		vnp_Params.put("vnp_Locale", "vn");
		vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
		vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
		vnp_Params.put("vnp_Locale", "vn");
		vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
		vnp_Params.put("vnp_OrderType", "other");
		vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
//	        String locate = req.getParameter("language");
//	        if (locate != null && !locate.isEmpty()) {
//	            vnp_Params.put("vnp_Locale", locate);
//	        } else {
//	            vnp_Params.put("vnp_Locale", "vn");
//	        }

		Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String vnp_CreateDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

		cld.add(Calendar.MINUTE, 15);
		String vnp_ExpireDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

		List fieldNames = new ArrayList(vnp_Params.keySet());
		Collections.sort(fieldNames);
		StringBuilder hashData = new StringBuilder();
		StringBuilder query = new StringBuilder();
		Iterator itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) vnp_Params.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				// Build hash data
				hashData.append(fieldName);
				hashData.append('=');
				hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				// Build query
				query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
				query.append('=');
				query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				if (itr.hasNext()) {
					query.append('&');
					hashData.append('&');
				}
			}
		}
		String queryUrl = query.toString();
		String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
		queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
		String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
		payment pay = new payment();
		pay.setStatus("ok");
		pay.setMesseage("Successfully");
		pay.setUrl(paymentUrl);
//	        com.google.gson.JsonObject job = new JsonObject();
//	        job.addProperty("code", "00");
//	        job.addProperty("message", "success");
//	        job.addProperty("data", paymentUrl);
//	        Gson gson = new Gson();
//	        resp.getWriter().write(gson.toJson(job));
		Map<String,String> response = new HashMap<>();
		response.put("url", paymentUrl);
		return ResponseEntity.ok(response);

	}

}
