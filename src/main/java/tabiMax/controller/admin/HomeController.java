package tabiMax.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@Controller("homeAdmin")
public class HomeController {
	@RequestMapping(value = "/admin-trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("/admin/home");
		return mav;
	}


	@RequestMapping(path = "/async", method = RequestMethod.GET)
	public DeferredResult<View> getAsync() {
	  DeferredResult<View> deferredResult = new DeferredResult<>();
	  View view = new View() {
		
		@Override
		public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			response.sendRedirect("/trang-chu");
			
		}
	};
	  // Start a new thread to perform a long-running task.
	  new Thread(() -> {
	    // Do some work.
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    // Set the view that you want to render.
	   

	    // Render the view.
	    deferredResult.setResult(view);
	  }).start();

	  return deferredResult;
	}
}
