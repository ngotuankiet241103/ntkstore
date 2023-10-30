package tabiMax.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "carts")
public class CartsEntity extends BaseEntity {
	@OneToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "cart")
	@JsonManagedReference
	private Set<CartItemEntity> cartItems = new HashSet<>();

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	
	public Set<CartItemEntity> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItemEntity> cartItems) {
		this.cartItems = cartItems;
	}

	@Override
	public String toString() {
		return "CartsEntity [user=" + user + ", cartItems=" + cartItems.size() + "]";
	}
	
}
