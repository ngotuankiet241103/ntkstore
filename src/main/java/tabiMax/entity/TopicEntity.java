package tabiMax.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class TopicEntity extends BaseEntity{
	@Column
	private String name;
	@Column
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
	public TopicEntity(String name, String code) {
		
		this.name = name;
		this.code = code;
	}
	public TopicEntity() {
		
	}
	
	
	
}
