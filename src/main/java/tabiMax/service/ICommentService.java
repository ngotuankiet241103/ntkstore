package tabiMax.service;

import java.util.List;
import java.util.Map;

import tabiMax.dto.CommentDTO;
import tabiMax.entity.CommentEntity;

public interface ICommentService {

	CommentEntity save(CommentDTO comment);

	List<CommentEntity> findAllByBlogId(Long blog_id);

	Map<String,List<CommentEntity>> findByBlogId(Long id);

}
