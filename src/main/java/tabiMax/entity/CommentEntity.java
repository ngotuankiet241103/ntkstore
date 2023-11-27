package tabiMax.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {
	@Column
	private String comments;
	@Column
	private int node_left;
	@Column
	private int node_right;
	@Column
	private Long parent_id;
	@ManyToOne(fetch =  FetchType.EAGER)
	private UserEntity user;
	
	@ManyToOne
	@JsonIgnore
	private BlogEntity blog;

	private String tree_id;

	private boolean isRoot;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getNode_left() {
		return node_left;
	}

	public void setNode_left(int node_left) {
		this.node_left = node_left;
	}

	public int getNode_right() {
		return node_right;
	}

	public void setNode_right(int node_right) {
		this.node_right = node_right;
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
	
	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	@Override
	public String toString() {
		return "CommentEntity [comments=" + comments + ", node_left=" + node_left + ", node_right=" + node_right
				+ ", user=" + user + ", blog=" + blog + ", tree_id=" + tree_id + ", isRoot=" + isRoot + "]";
	}
	
}
