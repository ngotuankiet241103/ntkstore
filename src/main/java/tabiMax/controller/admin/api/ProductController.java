package tabiMax.controller.admin.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import tabiMax.contraint.MaxPageItem;
import tabiMax.paging.pageRequest;
import tabiMax.service.IProductService;

@Controller("apiProduct")
@RequestMapping(value ="/api")
public class ProductController {
	@Autowired
	private IProductService productService;
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	
	@GetMapping(value = "/product")
	public ResponseEntity<Map<String,Object>> getAllProduct(@ModelAttribute("paging") pageRequest paging) {
		Pageable pageable = null;
		if (paging.getPage() != null) {
			pageable =  new PageRequest(paging.getPage(), MaxPageItem.maxPageItem_product);
		} else if (paging.getPage() == null) {
			pageable =  new PageRequest(MaxPageItem.pageStart, MaxPageItem.maxPageItem_product);
		}
//		List<ProductEntity> productEntity =productService.findAll(pageable).getContent();
	    return ResponseEntity.ok(productService.findAll(pageable));
	}
}
