package tabiMax.controller.admin;

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
import tabiMax.paging.pageRequest;
import tabiMax.service.IRoleService;
import tabiMax.service.IUserService;

@Controller("managerUser")
@RequestMapping("/admin")
public class UserController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
	@GetMapping("/manager/user")
	public String getPageManagerUser(Model model,@ModelAttribute("pagable") pageRequest paging) {
		Pageable pageable = null;	
		if (paging.getPage() != null) {
			pageable =   new PageRequest(paging.getPage(),  MaxPageItem.maxPageItem_product);
		}
		else if(paging.getPage() == null) {
			pageable =  new PageRequest(MaxPageItem.pageStart, MaxPageItem.maxPageItem_product);
		}
		Map<String, Object> response = userService.findAll(pageable);
		model.addAttribute("users", response.get("users"));
		model.addAttribute("paging", response.get("paging"));
		model.addAttribute("roles", roleService.findAll());
		return "admin/user";
	}
	
}
