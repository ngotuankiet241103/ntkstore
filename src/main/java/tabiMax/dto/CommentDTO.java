package tabiMax.dto;

public class CommentDTO {
	private Long blogId;
	private String content;
	private Long userId;
	private String tree_id;
	private Long parent_id;
	public Long getBlogId() {
		return blogId;
	}
	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTree_id() {
		return tree_id;
	}
	public void setTree_id(String tree_id) {
		this.tree_id = tree_id;
	}
	public Long getParent_id() {
		return parent_id;
	}
	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}
	
}
