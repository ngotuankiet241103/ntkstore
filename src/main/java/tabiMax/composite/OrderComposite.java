package tabiMax.composite;

import java.util.List;

public class OrderComposite {
	private String userName;
	
	private String email;
	
	private String phone;
	
	private String address;
	
	private List<Integer> quantity;
	
	private List<Float> totalPrice;
	
	private List<String> size;
	
	private List<Long> productId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Integer> getQuantity() {
		return quantity;
	}

	public void setQuantity(List<Integer> quantity) {
		this.quantity = quantity;
	}

	public List<Float> getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(List<Float> totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<String> getSize() {
		return size;
	}

	public void setSize(List<String> size) {
		this.size = size;
	}

	public List<Long> getProductId() {
		return productId;
	}

	public void setProductId(List<Long> productId) {
		this.productId = productId;
	}
	
	
}
