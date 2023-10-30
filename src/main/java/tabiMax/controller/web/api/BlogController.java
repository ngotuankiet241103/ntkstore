package tabiMax.controller.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tabiMax.dto.CommentDTO;
import tabiMax.entity.BlogEntity;
import tabiMax.entity.CommentEntity;
import tabiMax.service.IBlogSerivce;
import tabiMax.service.ICommentService;

@RestController("apiBlog")
public class BlogController {
	@Autowired
	private ICommentService commentService;
	@Autowired
	private IBlogSerivce blogSerivce;
	@GetMapping("/api/blog/comment/{id}")
	public ResponseEntity<List<CommentEntity>> getComments(@PathVariable("id") Long blog_id){
		return ResponseEntity.ok(commentService.findAllByBlogId(blog_id));
	}
//	@GetMapping("/api/blog/{blogCode}")
//	public ResponseEntity<BlogEntity> getBlogByCode(@PathVariable("blogCode") String code){
//		return ResponseEntity.ok(blogSerivce.findByCode(code));
//	}
	@PostMapping("/api/blog/comment")
	public ResponseEntity<CommentEntity> addComment(@RequestBody CommentDTO comment){
		return ResponseEntity.ok(commentService.save(comment));
	}
}
