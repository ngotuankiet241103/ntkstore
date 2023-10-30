package tabiMax.controller.web.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tabiMax.entity.CommentEntity;
import tabiMax.service.ICommentService;

@RestController
public class CommentController {
	@Autowired
	private ICommentService commentService;
	@GetMapping("/api/blog/{blogId}/comment")
	public ResponseEntity<Map<String,List<CommentEntity>>> getCommentByBlogId(@PathVariable("blogId") Long id){
		return ResponseEntity.ok(commentService.findByBlogId(id));
	}
}
