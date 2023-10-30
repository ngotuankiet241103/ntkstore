package tabiMax.controller.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tabiMax.dto.CategoryCommonDTO;
import tabiMax.dto.CategoryDTO;
import tabiMax.service.ICategoryCommonService;
import tabiMax.service.ICategoryService;

@RestController("api-category")
@RequestMapping("/api")
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private ICategoryCommonService categoryCommonService;
	@GetMapping("/category")
	public ResponseEntity<List<CategoryCommonDTO>> getCategoryCommon(){
		return  ResponseEntity.ok(categoryCommonService.findAll());
	}
	@GetMapping("/category/details/{id}/category")
	public ResponseEntity<List<CategoryDTO>> getByCategoryId(@PathVariable("id") Long categoryId){
		return  ResponseEntity.ok(categoryService.findByCategoryId(categoryId));
	}
}
