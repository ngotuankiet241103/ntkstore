package tabiMax.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String image;

	@Column
	private String fullName;

	@Column
	private String phone;
	@Column
	private boolean enabled;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public UserEntity(String email, String password, String fullName, boolean enabled, RoleEntity role) {
		super();
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.enabled = enabled;
		this.role = role;
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

	public UserEntity() {
		super();
	}

	@Column
	private int status;
//	@ManyToMany(fetch = FetchType.EAGER,
//			cascade = {CascadeType.PERSIST})
//	@JoinTable(name = "role_user", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
//	@JsonManagedReference
	@ManyToOne
	private RoleEntity role;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

//	public Set<RoleEntity> getRole() {
//		return role;
//	}
//
//	public void setRole(Set<RoleEntity> role) {
//		this.role = role;
//	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public UserEntity(String email, String password, String fullName, RoleEntity role) {
		super();
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
