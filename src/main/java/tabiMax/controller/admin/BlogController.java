package tabiMax.controller.admin;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tabiMax.contraint.MaxPageItem;
import tabiMax.dto.BlogDTO;
import tabiMax.paging.pageRequest;
import tabiMax.service.FileUpload;
import tabiMax.service.IBlogSerivce;
import tabiMax.service.ITopicService;
import tabiMax.utils.tranfromListToMap;

@Controller
@RequestMapping("/admin")
public class BlogController {
	@Autowired
	private IBlogSerivce blogSerivce;
	@Autowired
	private ITopicService topicService;
	@Autowired
	private FileUpload fileUpload;

	@GetMapping("/blog")
	public String getBlogPage(Model model, @ModelAttribute("paging") pageRequest paging) {
		Pageable pageable = null;
		if (paging.getPage() != null) {
			pageable = new PageRequest(paging.getPage(), MaxPageItem.maxPageItem_product);
		} else if (paging.getPage() == null) {
			pageable = new PageRequest(MaxPageItem.pageStart, MaxPageItem.maxPageItem_product);
		}
		Map<String, Object> response = blogSerivce.findAll(pageable);
		model.addAttribute("blogs", response.get("blogs"));

		return "admin/blog";
	}

	@GetMapping("/blog/add")
	public String getPageBlogAdd(Model model) {
		model.addAttribute("topics", topicService.findAll());
		return "admin/blogAdd";
	}

	@PostMapping("/blog/add")
	public String addBlog(@ModelAttribute("blog") BlogDTO blog) {
		try {
			if (blog.getFileImage() != null) {
				String url = fileUpload.uploadFile(blog.getFileImage());
				blog.setImage(url);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		blogSerivce.save(blog);
		return "redirect:/admin/blog";
	}
}
