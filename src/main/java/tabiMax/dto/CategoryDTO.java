package tabiMax.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CategoryDTO {
	private Long id;
	private String name;
	private String code;
	private CategoryCommonDTO category;
	@JsonIgnore
	private Long categoryId;
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	@Override
	public String toString() {
		return "CategoryDTO [id=" + id + ", name=" + name + ", code=" + code + ", category=" + category + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public CategoryCommonDTO getCategory() {
		return category;
	}
	public void setCategory(CategoryCommonDTO category) {
		this.category = category;
	}
	
	
}
