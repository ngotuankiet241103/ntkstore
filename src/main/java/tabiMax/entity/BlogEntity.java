package tabiMax.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "blog")
public class BlogEntity extends BaseEntity {
	@Column
	private String title;
	@Column(columnDefinition = "TEXT")
	private String content;
	@Column
	private String image;
	@Column(columnDefinition = "TEXT")
	private String description;
	@Column
	private String code;
	@ManyToOne
	private TopicEntity topic;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "blog_tag", joinColumns = @JoinColumn(name = "blogId"), inverseJoinColumns = @JoinColumn(name = "tagId"))
	
	private List<TagEntity> tags;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
	
	private List<CommentEntity> comments = new ArrayList<>();

	public List<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}

	public BlogEntity(String title, String content, TopicEntity topic, List<TagEntity> tags) {

		this.title = title;
		this.content = content;
		this.topic = topic;
		this.tags = tags;
	}

	public BlogEntity() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public TopicEntity getTopic() {
		return topic;
	}

	public void setTopic(TopicEntity topic) {
		this.topic = topic;
	}

	public List<TagEntity> getTags() {
		return tags;
	}

	public void setTags(List<TagEntity> tags) {
		this.tags = tags;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
