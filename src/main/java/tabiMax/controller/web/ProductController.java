package tabiMax.controller.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import tabiMax.contraint.MaxPageItem;
import tabiMax.dto.CategoryDTO;
import tabiMax.dto.ProductDTO;
import tabiMax.dto.ReviewDTO;
import tabiMax.paging.pageRequest;
import tabiMax.service.ICategoryCommonService;
import tabiMax.service.ICategoryService;
import tabiMax.service.IProductService;
import tabiMax.service.IReviewService;

@Controller("product")

public class ProductController {
	@Autowired
	private IProductService productService;
	@Autowired
	private IReviewService reviewService;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private ICategoryCommonService categoryCommonService;
	@GetMapping("/product")
	public String productPage(Model model, @ModelAttribute("paging") pageRequest paging) {
		Pageable pageable = null;
		if (paging.getPage() != null) {
			pageable = new PageRequest(paging.getPage(), MaxPageItem.maxPageItem_product);
		} else if (paging.getPage() == null) {
			pageable = new PageRequest(MaxPageItem.pageStart, MaxPageItem.maxPageItem_product);
		}
		Map<String, Object> response = productService.findAll(pageable);
//		Page<ProductEntity> pageProduct = productService.findAll(pageable);
//		List<product> listProduct = entityToDto.entityToDto(pageProduct, product.class);
//		paging.setTotalsPage(pageProduct.getTotalPages());
//		paging.setPage(pageProduct.getNumber());
		model.addAttribute("model", response.get("products"));
		model.addAttribute("pageable", response.get("pagination"));
		
		return "web/product";
	}

	@GetMapping("/product/{nameCategory}")
	public String productByCagegory(@PathVariable("nameCategory") String categoryName, Model model,
			@ModelAttribute("paging") pageRequest paging) {
		Pageable pageable = null;
		if (paging.getPage() != null) {
			pageable = new PageRequest(paging.getPage(), MaxPageItem.maxPageItem_product);
		} else if (paging.getPage() == null) {
			pageable = new PageRequest(MaxPageItem.pageStart, MaxPageItem.maxPageItem_product);
		}
		Map<String, Object> response = productService.findByCategoryName(categoryName, pageable);
//		
		model.addAttribute("model", response.get("products"));
		model.addAttribute("pageable", response.get("pagiantion"));
		return "web/product";
	}
	@GetMapping("/product/collections/{nameCategory}")
	public String productByCagegoryDetails(@PathVariable("nameCategory") String categoryName, Model model,
			@ModelAttribute("paging") pageRequest paging) {
		Pageable pageable = null;
		if (paging.getPage() != null) {
			pageable = new PageRequest(paging.getPage(), MaxPageItem.maxPageItem_product);
		} else if (paging.getPage() == null) {
			pageable = new PageRequest(MaxPageItem.pageStart, MaxPageItem.maxPageItem_product);
		}
		
		Map<String, Object> response = productService.findByCategoryDetailsName(categoryName, pageable);
	
		model.addAttribute("model", response.get("products"));
		model.addAttribute("pageable", response.get("pagination"));
		model.addAttribute("categoryName",categoryName);
		return "web/product";
	}
	@GetMapping("/product/detail/{productCode}")
	public String detailProductPage(@PathVariable("productCode") String productCode, Model model) {
		ProductDTO product = productService.findByCode(productCode);
		List<ReviewDTO> review =  reviewService.findByProductId(product.getId());
	    model.addAttribute("products", productService.findRelateProduct(product));
		model.addAttribute("product", product);
		model.addAttribute("reviews", review);
		return "web/detailsProduct";
	}
	

	@PostMapping("/product/order")
	public void orderProduct() {

	}
}
