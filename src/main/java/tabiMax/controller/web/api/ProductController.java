package tabiMax.controller.web.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import tabiMax.contraint.MaxPageItem;
import tabiMax.dto.CartItemDTO;
import tabiMax.dto.ProductDTO;
import tabiMax.entity.CartItemEntity;
import tabiMax.paging.pageRequest;
import tabiMax.service.ICartService;
import tabiMax.service.IProductService;

@Controller("apiCart")
public class ProductController {
	@Autowired
	private ICartService cartService;
	@Autowired
	private IProductService productService;

//	@GetMapping("/api/product/{categoryName}")
//	public ResponseEntity<Map<String, Object>> getProductByCategoryName(
//			@PathVariable("categoryName") String categoryName, @ModelAttribute("paging") pageRequest paging) {
//		Pageable pageable = null;
//		if (paging.getPage() != null) {
//			pageable = new PageRequest(paging.getPage(), MaxPageItem.maxPageItem_product);
//		} else if (paging.getPage() == null) {
//			pageable = new PageRequest(MaxPageItem.pageStart, MaxPageItem.maxPageItem_product);
//		}
//		return ResponseEntity.ok(productService.findByCategoryName(categoryName, pageable));
//	}
	@GetMapping("/api/product/by")
	public ResponseEntity<Map<String, Object>> getProductBy(
			@RequestParam(value = "size",defaultValue = "") String sizes , 
			@RequestParam(value = "orderBy",defaultValue = "") String orderBy,
			@RequestParam(value ="sortBy",defaultValue = "") String sortBy,
			@ModelAttribute("paging") pageRequest paging){
		Map<String, Object> response = null;
		Sort sort = null;
		if(!orderBy.equals("")) {
			sort = sortBy.equals("ASC") ?  new Sort(orderBy) : new Sort(Sort.Direction.DESC, orderBy);
		}
		Pageable pageable = null;
		if (paging.getPage() != null) {
			pageable = new PageRequest(paging.getPage(), MaxPageItem.maxPageItem_product,sort);
		} else if (paging.getPage() == null) {
			pageable = new PageRequest(MaxPageItem.pageStart, MaxPageItem.maxPageItem_product, sort);
		}
		if(!sizes.equals("")) {
			response = productService.findBySize(sizes);
		}
		
		
		return ResponseEntity.ok(response);
	}
	@GetMapping("/api/product/{categoryDetailsName}")
	public ResponseEntity<Map<String, Object>> getProductByCategoryDetailsName(
			@PathVariable("categoryDetailsName") String categoryName, @ModelAttribute("paging") pageRequest paging) {
		Pageable pageable = null;
		if (paging.getPage() != null) {
			pageable = new PageRequest(paging.getPage(), MaxPageItem.maxPageItem_product);
		} else if (paging.getPage() == null) {
			pageable = new PageRequest(MaxPageItem.pageStart, MaxPageItem.maxPageItem_product);
		}
		return ResponseEntity.ok(productService.findByCategoryDetailsName(categoryName, pageable));
	}
	@GetMapping(value = "/api/product/order/{userId}")
	public ResponseEntity<?> getA(){
		return ResponseEntity.ok("siu");
	}
	@GetMapping("/api/product/relate/{productName}")
	public ResponseEntity<?> getProductsRelate(@PathVariable("productName") String productName){
		ProductDTO product = productService.findByName(productName);
		return ResponseEntity.ok(productService.findRelateProduct(product));
	}
//	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/api/product/order/{userId}")
	public ResponseEntity<CartItemEntity> addProductToCart(@RequestBody CartItemDTO cart) {
		System.out.println(cart.getProductId());
		return ResponseEntity.ok(cartService.save(cart));
	}

}
