package tabiMax.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import tabiMax.auth.CustomUserDetails;


public class UserIdInterceptor  implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
                CustomUserDetails userDetails =  (CustomUserDetails) authentication.getPrincipal();
                Long userId = userDetails.getUserId();
                String image = userDetails.getImage();
                
                System.out.println(userId);
                modelAndView.addObject("userId", 1);
                modelAndView.addObject("avatar", image);
               
            }
        }
    }
}
