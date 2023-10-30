package tabiMax.utils;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import tabiMax.auth.CustomUserDetails;

public class InterceptorUtils {
	public static void preHandle(Authentication auth,Model model) {
		if(auth != null) {
			CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
			
			model.addAttribute("userId" , userDetails.getUserId());
			model.addAttribute("userImage", userDetails.getImage());
			
		}
		
	}
}
