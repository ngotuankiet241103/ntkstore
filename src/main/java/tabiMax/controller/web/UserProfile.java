package tabiMax.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserProfile {
	@GetMapping("/profile")
	public String pageProfile() {
		
		return "web/userProfile";
	}
	@GetMapping("/profile/user")
	public String pageDetailsProfile() {
		
		return "web/detailsUser";
	}
	
}
