package tabiMax.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@Entity
public class abc extends BaseEntity{
	@Column
	private String comment;
	@Column
	private int left;
	@Column
	private int right;
	@ManyToOne
	private UserEntity user;
	@ManyToOne
	private BlogEntity blog;

	private String tree_id;

	private boolean isRoot;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public BlogEntity getBlog() {
		return blog;
	}

	public void setBlog(BlogEntity blog) {
		this.blog = blog;
	}

	public String getTree_id() {
		return tree_id;
	}

	public void setTree_id(String tree_id) {
		this.tree_id = tree_id;
	}

	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
}
