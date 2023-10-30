package tabiMax.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import tabiMax.entity.DetailsOrder;
import tabiMax.entity.OrderItemEntity;
import tabiMax.entity.RefundOrderEntity;
import tabiMax.entity.UserEntity;

public class OrderDTO {
	
	private Long id;
	private String code;
	private float total;
	private String status;
	private String detailStatus;
	private String paymentStatus;
	private String paymentMethod;
	private UserEntity user;
	private DetailsOrderDTO detailsOrder;
	private Set<OrderItemEntity> orderItems = new HashSet<>();
	private RefundOrderEntity refundOrder;
	private Date createdDate;
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Long getId() {
		return id;
	}
	public OrderDTO() {
		super();
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public float getTotal() {
		return total;
	}
	public OrderDTO(String status, String detailStatus) {
		super();
		this.status = status;
		this.detailStatus = detailStatus;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDetailStatus() {
		return detailStatus;
	}
	public void setDetailStatus(String detailStatus) {
		this.detailStatus = detailStatus;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	public DetailsOrderDTO getDetailsOrder() {
		return detailsOrder;
	}
	public void setDetailsOrder(DetailsOrderDTO detailsOrder) {
		this.detailsOrder = detailsOrder;
	}
	public Set<OrderItemEntity> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItemEntity> orderItems) {
		this.orderItems = orderItems;
	}
	public RefundOrderEntity getRefundOrder() {
		return refundOrder;
	}
	public void setRefundOrder(RefundOrderEntity refundOrder) {
		this.refundOrder = refundOrder;
	}
}
