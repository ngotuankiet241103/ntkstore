package tabiMax.dto;

public class CategoryCommonDTO {
	@Override
	public String toString() {
		return "CategoryCommonDTO [id=" + id + ", name=" + name + ", code=" + code + "]";
	}
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private String name;
	private String code;
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
	
}
