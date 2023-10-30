package tabiMax.controller.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import tabiMax.auth.CustomUserDetails;
import tabiMax.contraint.MaxPageItem;
import tabiMax.dto.BlogDTO;
import tabiMax.entity.BlogEntity;
import tabiMax.entity.UserEntity;
import tabiMax.paging.pageRequest;
import tabiMax.service.IBlogSerivce;
import tabiMax.service.ITopicService;
import tabiMax.service.IUserService;

@Controller("pageBlog")
public class BlogController {
	@Autowired
	private IBlogSerivce blogSerivce;
	@Autowired
	private ITopicService topicService;
	@Autowired
	private IUserService userService;
	@GetMapping("/blog")
	public String getPageBlog(Model model,@ModelAttribute("paging") pageRequest paging) {
		Pageable pageable = null;
		if (paging.getPage() != null) {
			pageable = new PageRequest(paging.getPage(), MaxPageItem.maxPageItem_product);
		} else if (paging.getPage() == null) {
			pageable = new PageRequest(MaxPageItem.pageStart, MaxPageItem.maxPageItem_product);
		}
		Map<String, Object> response = blogSerivce.findAll(pageable);
		model.addAttribute("blogs", response.get("blogs"));
		model.addAttribute("topics", topicService.findAll());
		return "web/blog";
	}
	@GetMapping("/blog/topic/{topicCode}")
	public String getPageBlogByTopic(@PathVariable("topicCode") String topicCode ,Model model,@ModelAttribute("paging") pageRequest paging) {
		Pageable pageable = null;
		if (paging.getPage() != null) {
			pageable = new PageRequest(paging.getPage(), MaxPageItem.maxPageItem_product);
		} else if (paging.getPage() == null) {
			pageable = new PageRequest(MaxPageItem.pageStart, MaxPageItem.maxPageItem_product);
		}
		Map<String, Object> response = blogSerivce.findByTopicCode(topicCode,pageable);
		model.addAttribute("blogs", response.get("blogs"));
		model.addAttribute("topics", topicService.findAll());
		return "web/blog";
	}
	@GetMapping("/blog/tag/{tagCode}")
	public String getPageBlogByTag(@PathVariable("tagCode") String tagCode ,Model model,@ModelAttribute("paging") pageRequest paging) {
		Pageable pageable = null;
		if (paging.getPage() != null) {
			pageable = new PageRequest(paging.getPage(), MaxPageItem.maxPageItem_product);
		} else if (paging.getPage() == null) {
			pageable = new PageRequest(MaxPageItem.pageStart, MaxPageItem.maxPageItem_product);
		}
		Map<String, Object> response = blogSerivce.findByTagCode(tagCode, pageable);
		model.addAttribute("blogs", response.get("blogs"));
		model.addAttribute("topics", topicService.findAll());
		return "web/blog";
	}
	@GetMapping("/blog/{codeBlog}")
	public String getDetailsBlog(@PathVariable("codeBlog") String code ,Model model) {
		BlogDTO blog =  blogSerivce.findByCode(code);
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long id = null;
		if(email.contains("@")) {
			UserEntity user = userService.findByEmail(email);
			id = user.getId();
		}
		
		model.addAttribute("blog", blog);
		model.addAttribute("userId", id);
		return "web/detailsBlog";
		
		
	}
}
