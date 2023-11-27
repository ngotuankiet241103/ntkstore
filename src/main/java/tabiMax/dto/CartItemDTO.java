package tabiMax.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CartItemDTO {

	private float totalPrice;

	private String size;

	private int quanlity;
	
	private Long productId;

	private ProductDTO product;
	
	public CartItemDTO() {
		
	}
	
	public CartItemDTO(float totalPrice, String size, int quanlity, Long productId) {
		this.totalPrice = totalPrice;
		this.size = size;
		this.quanlity = quanlity;
		this.productId = productId;
	}

	public CartItemDTO(float totalPrice, String size, int quanlity) {
		
		this.totalPrice = totalPrice;
		this.size = size;
		this.quanlity = quanlity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "cart [totalPrice=" + totalPrice + ", size=" + size + ", quanlity=" + quanlity + ", productId="
				+ productId + "]";
	}

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

	public int getQuanlity() {
		return quanlity;
	}

	public void setQuanlity(int quanlity) {
		this.quanlity = quanlity;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

}
