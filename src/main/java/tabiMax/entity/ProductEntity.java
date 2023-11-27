package tabiMax.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity implements Serializable {

	@Column
	private String name;
	@Column
	private String image;
	@Column(columnDefinition = "TEXT")
	private String description;
	@Column
	private Float price;
	@Column
	private String code;
	@Column
	private int status;
	@Column
	private int discount;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "sizeQuantityMap")
	@MapKeyColumn(name = "size")
	private Map<String, Integer> sizeQuantityMap;
	@ManyToOne
	private CategoryEntity categoryDetails;
	
	@ManyToOne
	private CategoryCommonEntity category;
	@OneToMany(mappedBy = "product")
	@JsonIgnore
	private Set<CartItemEntity> cartItems = new  HashSet<>();
	
	

	public Set<CartItemEntity> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItemEntity> cartItems) {
		this.cartItems = cartItems;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Map<String, Integer> getSizeQuantityMap() {
		return sizeQuantityMap;
	}

	public void setSizeQuantityMap(Map<String, Integer> sizeQuantityMap) {
		this.sizeQuantityMap = sizeQuantityMap;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	public CategoryEntity getCategoryDetails() {
		return categoryDetails;
	}
	public void setCategoryDetails(CategoryEntity categoryDetails) {
		this.categoryDetails = categoryDetails;
	}

	public CategoryCommonEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryCommonEntity category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	

}
