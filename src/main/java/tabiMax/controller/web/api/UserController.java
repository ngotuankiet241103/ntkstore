package tabiMax.controller.web.api;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import tabiMax.auth.CustomUserDetails;
import tabiMax.auth.PasswordUpdateRequest;
import tabiMax.dto.UserDTO;
import tabiMax.entity.UserEntity;
import tabiMax.service.IUserService;

@Controller("apiUserController")
public class UserController {

//	
//	@Qualifier("userService")
	private IUserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	public UserController(IUserService userService) {

		this.userService = userService;
	}
//	@GetMapping(value = "/api/createUser")
//	public void createUser() {
//		RoleEntity role_user = new RoleEntity();
//		RoleEntity role_admin = new RoleEntity();
//		role_user.setCode("nguoi-dung");
//		role_user.setName("User");
//		role_admin.setCode("quan_tri");
//		role_admin.setName("admin");
//		Set<RoleEntity> roles = new HashSet<>();
//		roles.add(role_admin);
//		roles.add(role_user);
//		
//		UserEntity user = new UserEntity();
//		user.setFullName("kiet");
//		user.setRole(roles);
//		userService.save(user);
//		
//	    
//	}
	@GetMapping(value = "/api/user/get/{fullName}")
	public UserEntity getUserByFullName(@PathVariable("fullName") String fullName) {
	    return userService.findByFullName(fullName);
	}
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping(value = "/api/user/{id}")
	public ResponseEntity<UserEntity> getUser(@PathVariable("id") Long id) {
		UserEntity user = userService.findById(id);
		
	    return ResponseEntity.ok(user);
	}
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping(value = "/api/user")
	public ResponseEntity<?> getAllUser(Principal princial) {
		Object email = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(email);
		CustomUserDetails user = null;
		if(email.equals("anonymousUser")) {
			return ResponseEntity.ok("null");
			
		}
		user = (CustomUserDetails) email;
	    return ResponseEntity.ok(userService.findByEmails(user.getUsername()));
	}
	@GetMapping(value = "/api/role/{id}/user")
	public ResponseEntity<List<UserEntity>> getUserByRoleId(@PathVariable("id") Long id) {
	   return ResponseEntity.ok(userService.findUserByRoleId(id));
	}
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PutMapping("/api/user/{id}")
	public ResponseEntity<?> updateUserById(@RequestBody PasswordUpdateRequest user,
			@PathVariable("id")Long id) {
		if (user == null || id == null) {
		    return ResponseEntity.badRequest().build();
		  }

		  // Check if the user exists.
		  UserEntity existingUser = userService.findById(id);
		  if (existingUser == null) {
		    return ResponseEntity.notFound().build();
		  }
		 
		  UserEntity  entity = userService.updateUserById(user,id);
		  // Update the user.
		  if (entity == null) {
			 return ResponseEntity.badRequest().body("Passwords do not match.");
		  }
		   return ResponseEntity.ok(entity);
				   
	}
}
;