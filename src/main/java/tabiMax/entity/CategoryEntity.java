package tabiMax.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detailsCategory")
public class CategoryEntity  extends BaseEntity implements Serializable{
	
	@Override
	public String toString() {
		return "CategoryEntity [code=" + code + ", name=" + name + "]";
	}
	@Column
	private String code;
	@Column
	private String name;
	@ManyToOne
	private CategoryCommonEntity category;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CategoryCommonEntity getCategory() {
		return category;
	}
	public void setCategory(CategoryCommonEntity category) {
		this.category = category;
	}
	
	
}
