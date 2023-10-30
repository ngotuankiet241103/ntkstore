package tabiMax.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tabiMax.auth.AuthenticationRequest;
import tabiMax.auth.AuthenticationResponse;
import tabiMax.auth.RefeshTokenRequest;
import tabiMax.auth.RegisterService;
import tabiMax.auth.RegistrationRequest;
import tabiMax.service.IUserService;

@Controller
public class loginController {

	@Autowired
	private IUserService userService;
	@Autowired
	private RegisterService registerService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("login/login");
		return mav;
	}
	@RequestMapping(value = "/admin/authenticated", method = RequestMethod.GET)
	public ModelAndView loginAdminPage() {
		ModelAndView mav = new ModelAndView("login/authenticated");
		return mav;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView registerPage() {
		ModelAndView mav = new ModelAndView("login/register");
		return mav;
	}

//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	public String registerAccount(@Valid @ModelAttribute("user") UserDTO user, 
//			BindingResult bindingResult,
//			RedirectAttributes redirectAttributes) {
//		if (bindingResult.hasErrors()) {
//			   List<ObjectError> errors = bindingResult.getAllErrors();
//		        // process errors to get the value you want
//		        String value = errors.get(0).getDefaultMessage();
//		        redirectAttributes.addAttribute("paramName", value);
//		        return "redirect:/register";
//		}
//		userService.save(user);
//
//		return "redirect:/trang-chu";
//	}
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationRequest request) {
		return ResponseEntity.ok(registerService.register(request));
	}

	@GetMapping("/register/confirm")
	public ModelAndView confirm(@RequestParam("token") String token) {
		registerService.confirmToken(token);
		ModelAndView mv = new ModelAndView();
        mv.setViewName(null);
        return mv;
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<AuthenticationResponse> authenticated(
			@Valid @RequestBody AuthenticationRequest user) {
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
//		if (bindingResult.hasErrors()) {
//			   List<ObjectError> errors = bindingResult.getAllErrors();
//		        // process errors to get the value you want
//		        String value = errors.get(0).getDefaultMessage();
//		        redirectAttributes.addAttribute("paramName", value);
//		}
		return ResponseEntity.ok(userService.authenticate(user));
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PostMapping(value = "/refesh-token")
	public ResponseEntity<?> refeshToken(@RequestBody RefeshTokenRequest refeshToken) {
		System.out.println(refeshToken.getRefeshToken());
		AuthenticationResponse response = userService.refeshToken(refeshToken.getRefeshToken());
		if (response == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refesh Token hết hạn! ");
		}

		return ResponseEntity.ok(response);
	}
}
