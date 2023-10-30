package tabiMax.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tabiMax.entity.CategoryEntity;

public class ProductDTO {

	private Long id;

	private String name;

	private String image;
	@JsonIgnore
	private MultipartFile fileImage;

	private CategoryDTO categoryDetails;

	private Map<String, Integer> sizeQuantityMap;
	private List<String> size;
	private List<String> amount;
	private String description;
	private String code;
	private Float price;

	@JsonIgnore
	private Long categoryId;
	@JsonIgnore
	private Long categoryCommonId;
	private double distance;
	public Long getCategoryCommonId() {
		return categoryCommonId;
	}

	public void setCategoryCommonId(Long categoryCommonId) {
		this.categoryCommonId = categoryCommonId;
	}

	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public List<String> getSize() {
		return size;
	}

	public void setSize(List<String> size) {
		this.size = size;
	}

	public List<String> getAmount() {
		return amount;
	}

	public void setAmount(List<String> amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CategoryDTO getCategoryDetails() {
		return categoryDetails;
	}

	public void setCategoryDetails(CategoryDTO categoryDetails) {
		this.categoryDetails = categoryDetails;
	}

	public Map<String, Integer> getSizeQuantityMap() {
		return sizeQuantityMap;
	}

	public void setSizeQuantityMap(Map<String, Integer> sizeQuantityMap) {
		this.sizeQuantityMap = sizeQuantityMap;
	}

	public MultipartFile getFileImage() {
		return fileImage;
	}

	public void setFileImage(MultipartFile fileImage) {
		this.fileImage = fileImage;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
