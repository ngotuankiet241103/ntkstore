package tabiMax.controller.admin.api;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudinary.http44.api.Response;

import tabiMax.composite.UserRequestUpdate;
import tabiMax.service.IUserService;



@Controller
@RequestMapping("/admin")
public class UserController {
	@Autowired
	private IUserService userService;
	@CrossOrigin(origins = "*")
	@PutMapping("/manager/user")
	public ResponseEntity<?> updateUser(@RequestBody UserRequestUpdate user) {

		return ResponseEntity.ok(userService.updateUserByRole(user));
	}
	@CrossOrigin(origins = "http://127.0.0.1:8080")
	@PutMapping("/api/manager/user/${id}")
	public ResponseEntity<?> updateStatusUser(@PathVariable("id") long id) {

		return ResponseEntity.ok(userService.updateEnableUser(id));
	}
	@CrossOrigin(origins = "http://127.0.0.1:8080")
	@DeleteMapping("/api/manager/user")
	public ResponseEntity<?> deleteUserById(@RequestParam("id") long id) {
		return ResponseEntity.ok(userService.updateEnableUser(id));
	}
}
