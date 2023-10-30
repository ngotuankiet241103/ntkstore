package tabiMax.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import tabiMax.dto.CommentDTO;
import tabiMax.entity.CommentEntity;

public interface ICommentRepository extends Repository<CommentEntity, Long> {

	CommentEntity findById(Long parent_id);

	CommentEntity save(CommentEntity newComment);
	@Transactional
	@Modifying
	@Query("UPDATE CommentEntity  c SET c.node_left = c.node_left + 2 WHERE c.node_left > ?1 AND c.tree_id = ?2 ")
	void updateLeftTree(int left, String tree_id);
	@Transactional
	@Modifying
	@Query("UPDATE CommentEntity  c SET c.node_right = c.node_right + 2 WHERE c.node_right > ?1 AND c.tree_id = ?2 ")
	void updateRightTree(int left, String tree_id);

	List<CommentEntity> findByBlogId(Long id);

}
