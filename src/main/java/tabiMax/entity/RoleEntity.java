package tabiMax.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;
//	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "role")
//	@JsonBackReference
//	private Set<UserEntity> user = new HashSet<>();

//	public Set<UserEntity> getUser() {
//		return user;
//	}
//
//	public void setUser(Set<UserEntity> user) {
//		this.user = user;
//	}

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
