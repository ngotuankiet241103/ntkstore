package tabiMax.controller.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tabiMax.dto.ProductDTO;
import tabiMax.service.ICategoryService;
import tabiMax.service.IProductService;
import tabiMax.service.IUserService;
import tabiMax.utils.InterceptorUtils;

@Controller
public class HomeContoller {
	@Autowired
	private IUserService userService;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IProductService productService;
	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage(Authentication auth, Model model) {
		Sort sort = new Sort(Sort.Direction.DESC, "createdBy");
		Pageable pageable = new PageRequest(0, 4,sort);
		InterceptorUtils.preHandle(auth, model);
		model.addAttribute("categories", categoryService.findAll());
		List<ProductDTO> products = productService.findNewProduct(pageable);
		System.out.println(products);
		model.addAttribute("products", products );
		userService.createAdmin();
		ModelAndView mav = new ModelAndView("/web/home");
		return mav;
	}
}
