package tabiMax.dto;

import tabiMax.entity.ProductEntity;

public class OrderItemDTO {
	private float totalPrice;
	
	private String size;
	
	private int quanlity;
	
	private Long productId;
	
	private ProductEntity product;
	
	private boolean isReview;
	
	public OrderItemDTO(float totalPrice, String size, int quanlity) {
	
		this.totalPrice = totalPrice;
		this.size = size;
		this.quanlity = quanlity;
		
	}
	
	
	public OrderItemDTO(float totalPrice, String size, int quanlity, Long productId) {
		
		this.totalPrice = totalPrice;
		this.size = size;
		this.quanlity = quanlity;
		this.productId = productId;
	}


	public OrderItemDTO() {
		
	}


	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}


	public boolean isReview() {
		return isReview;
	}


	public void setReview(boolean isReview) {
		this.isReview = isReview;
	}
	

}
