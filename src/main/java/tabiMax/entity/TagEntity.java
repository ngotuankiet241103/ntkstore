package tabiMax.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class TagEntity extends BaseEntity {
	@Column
	private String name;
	@Column
	private String code;
	@ManyToMany(mappedBy = "tags")
	private List<BlogEntity> blogs;
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
	public TagEntity(String name, String code) {
	
		this.name = name;
		this.code = code;
	}
	public TagEntity() {
		super();
	}
	
	
}
