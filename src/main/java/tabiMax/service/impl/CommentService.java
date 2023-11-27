package tabiMax.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import tabiMax.dto.CommentDTO;
import tabiMax.entity.CommentEntity;
import tabiMax.repository.IBlogRepository;
import tabiMax.repository.ICommentRepository;
import tabiMax.repository.IUserRepository;
import tabiMax.service.ICommentService;
@Service
public class CommentService implements ICommentService{
	@Autowired
	private ICommentRepository commenntRepository;
	@Autowired
	private IBlogRepository blogRepository;
	@Autowired
	private IUserRepository userRepository;
	@Override
	public CommentEntity save(CommentDTO comment) {
		String tree_id = comment.getTree_id();
		CommentEntity newComment = new CommentEntity();
		if(tree_id != null) {
			CommentEntity commentEntity = commenntRepository.findById(comment.getParent_id());
			updateTree(commentEntity);
			newComment.setTree_id(tree_id);
			newComment.setNode_left(commentEntity.getNode_left() + 1);
			newComment.setNode_right(commentEntity.getNode_left() + 2);
			newComment.setBlog(blogRepository.findById(comment.getBlogId()));
			newComment.setComments(comment.getContent());
			newComment.setParent_id(comment.getParent_id());
			newComment.setUser(userRepository.findById(comment.getUserId()));
		}
		else {
			UUID tree_idNew = UUID.randomUUID();
			newComment.setTree_id(tree_idNew.toString());
			newComment.setBlog(blogRepository.findById(comment.getBlogId()));
			newComment.setComments(comment.getContent());
			newComment.setNode_left(1);
			newComment.setNode_right(2);
			newComment.setUser(userRepository.findById(comment.getUserId()));
			return commenntRepository.save(newComment);
		}
		
		return commenntRepository.save(newComment);
	}
	@Async
	@Transactional
	public void updateTree(CommentEntity comment) {
		commenntRepository.updateLeftTree(comment.getNode_left(), comment.getTree_id());
		commenntRepository.updateRightTree(comment.getNode_left(), comment.getTree_id());
	}
	@Override
	public List<CommentEntity> findAllByBlogId(Long blog_id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, List<CommentEntity>> findByBlogId(Long id) {
		Map<String, List<CommentEntity>> response = commenntRepository.findByBlogId(id).stream().collect(Collectors.groupingBy(cmt -> cmt.getTree_id()));;
		return response;
	}
}
