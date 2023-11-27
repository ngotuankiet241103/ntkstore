package tabiMax.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import tabiMax.auth.CustomUserDetails;
import tabiMax.service.IUserService;

@Controller
public class UserProfile {
	@Autowired
	private IUserService userService;
	@GetMapping("/profile")
	public String pageProfile() {
		
		return "web/userProfile";
	}
	@GetMapping("/profile/user")
	public String pageDetailsProfile() {
		
		return "web/detailsUser";
	}
	
}
