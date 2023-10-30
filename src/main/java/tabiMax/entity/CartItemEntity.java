package tabiMax.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "cartItem")
public class CartItemEntity extends BaseEntity{
	@Column
	private float totalPrice;
	
	@Column
	private String size;
	
	@Column
	private int quanlity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cart_id")
	@JsonBackReference
	private CartsEntity cart;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private ProductEntity product;

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	

	@Override
	public String toString() {
		return "CartItemEntity [totalPrice=" + totalPrice + ", size=" + size + ", quanlity=" + quanlity + ", cart="
				+ cart + ", product=" + product + "]";
	}

	public int getQuanlity() {
		return quanlity;
	}

	public void setQuanlity(int quanlity) {
		this.quanlity = quanlity;
	}


	public CartsEntity getCart() {
		return cart;
	}

	public void setCart(CartsEntity cart) {
		this.cart = cart;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}
	
}
