package tabiMax.controller.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import tabiMax.contraint.MaxPageItem;
import tabiMax.paging.pageRequest;
import tabiMax.service.IProductService;

@Controller("searchController")
public class SearchController {
	@Autowired
	private IProductService productService;
	@GetMapping("/search")
	public String querySearch(@RequestParam("q") String query, Model model, @ModelAttribute("paging") pageRequest paging) {
		Pageable pageable = null;
		if (paging.getPage() != null) {
			pageable = new PageRequest(paging.getPage(), MaxPageItem.maxPageItem_product);
		} else if (paging.getPage() == null) {
			pageable = new PageRequest(MaxPageItem.pageStart, MaxPageItem.maxPageItem_product);
		}
		Map<String,Object> response = productService.queryProduct(query,pageable);
		model.addAttribute("products", response.get("products"));
		model.addAttribute("pageable", response.get("pageable"));
		model.addAttribute("query", query);
		return "web/search";
	}
}
