package tabiMax.controller.web.api;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tabiMax.contraint.MaxPageItem;
import tabiMax.service.IProductService;

@Controller("apiSearch")
@RequestMapping("/api")
public class SearchController {
	@Autowired
	private IProductService productService;
	@GetMapping("/search")
	public ResponseEntity<?> getResultSearch(@RequestParam("q") String query){
		Pageable pageable = new PageRequest(MaxPageItem.pageStart, 5);
		return ResponseEntity.ok(productService.querySearch(query,pageable));
	}
}
