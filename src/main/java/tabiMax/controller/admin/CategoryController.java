package tabiMax.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tabiMax.dto.CategoryCommonDTO;
import tabiMax.dto.CategoryDTO;
import tabiMax.service.ICategoryCommonService;
import tabiMax.service.ICategoryService;

@Controller
@RequestMapping("/admin")
public class CategoryController {
	@Autowired
	private ICategoryCommonService categoryCommonService;
	@Autowired
	private ICategoryService categoryService;
	@GetMapping("/category")
	public String pageCategory(Model model) {
		
		model.addAttribute("category", categoryCommonService.findAll());
		model.addAttribute("listCategoryDetails", categoryService.findAll());
		return "admin/category";
	}
	@PostMapping("/category")
	public String addCategory(@ModelAttribute("category") CategoryCommonDTO category) {
		System.out.println(category.getCode());
		categoryCommonService.save(category);
		return "redirect:/admin/category";
	}
	@PostMapping("/category/detail")
	public String addCategoryDetails(@ModelAttribute("category") CategoryDTO category) {
		System.out.println(category.getCode());
		categoryService.save(category);
		return "redirect:/admin/category";
	}
}
