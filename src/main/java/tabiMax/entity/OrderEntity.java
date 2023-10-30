package tabiMax.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {
	@Column
	private String code;
	@Column
	private float total;

	@Column
	private String status;
	@Override
	public String toString() {
		return "OrderEntity [code=" + code + ", total=" + total + ", status=" + status + ", detailStatus="
				+ detailStatus + ", paymentStatus=" + paymentStatus + ", paymentMethod=" + paymentMethod
				+ ", detailsOrder=" + detailsOrder + ", user=" + user + ", orderItems=" + orderItems + "]";
	}

	@Column
	private String detailStatus;
	@Column
	private String paymentStatus;
	@Column
	private String paymentMethod;
	@OneToOne
	@JoinColumn(name = "refundOrder_id")
	private RefundOrderEntity refundOrder;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "detailsOrder_id")
	private DetailsOrder detailsOrder;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
	@OneToMany(mappedBy = "order",fetch = FetchType.EAGER)
	private Set<OrderItemEntity> orderItems = new HashSet<>();

	public OrderEntity() {

	}
	
	public OrderEntity(String code, float total, String status, String detailStatus, String paymentStatus,
			String paymentMethod, RefundOrderEntity refundOrder, DetailsOrder detailsOrder, UserEntity user,
			Set<OrderItemEntity> orderItems) {
		super();
		this.code = code;
		this.total = total;
		this.status = status;
		this.detailStatus = detailStatus;
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;
		this.refundOrder = refundOrder;
		this.detailsOrder = detailsOrder;
		this.user = user;
		this.orderItems = orderItems;
	}

	public OrderEntity(String code, String status, String detailStatus, String paymentStatus, String paymentMethod,
			DetailsOrder detailsOrder) {

		this.code = code;
		this.status = status;
		this.detailStatus = detailStatus;
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;
		this.detailsOrder = detailsOrder;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DetailsOrder getDetailsOrder() {
		return detailsOrder;
	}

	public void setDetailsOrder(DetailsOrder detailsOrder) {
		this.detailsOrder = detailsOrder;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Set<OrderItemEntity> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItemEntity> orderItems) {
		this.orderItems = orderItems;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
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
}
