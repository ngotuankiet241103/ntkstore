package tabiMax.contraint;

import java.util.ArrayList;
import java.util.List;

public class OrderStatus {
	public static String common_cancel = "Đã hủy";
	public static String common_handle =  "Đang xử lý";
	public static String common_delivered="Đã giao hàng";
	public static String common_refund ="hoàn hàng";
	public static String details_cancel = "Đã hủy";
	public static String details_handle="Đang xử lý";
	public static String details_pack ="Đã đóng gói";
	public static String details_delivery = "Đang giao hàng";
	public static String details_delivered = "Đã giao hàng";
	public static String details_refund = "Đã hoàn hàng";
	public static String paymentStatus_pending  = "pending";
	public static String paymentStatus_paid ="paid";
	public static String paymentMethod_cod = "COD";
	public static String paymentMethod_credit ="Credit-card";
	public static String statusRefund_pending = "Đang chờ hoàn tiền";
	public static String statusRefund_paid = "Đã hoàn tiền";
	
	public static List<String> getStatusAvailble() {
		List<String> status = new ArrayList<>();
		status.add(common_cancel);
		status.add(common_handle);
		status.add(common_delivered);
		return status;
		
	}
	public static List<String> getStatus() {
		List<String> status = new ArrayList<>();
		status.add(common_cancel);
		status.add(common_handle);
		status.add(common_delivered);
		status.add(common_refund);
		return status;
		
	}
	public static List<String> getDetailStatus(){
		List<String> status = new ArrayList<>();
		status.add(details_cancel);
		status.add(details_handle);
		status.add(details_pack);
		status.add(details_delivery);
		return status;
	}
}
