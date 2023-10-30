package tabiMax.controller.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import tabiMax.dto.RoleDTO;
import tabiMax.entity.RoleEntity;
import tabiMax.service.IRoleService;

@Controller
public class RoleController {
	private IRoleService roleService;
	@Autowired
	public RoleController(IRoleService roleService) {
		this.roleService = roleService;
	}
	@GetMapping(value = "/api/role")
	public ResponseEntity<List<RoleDTO>> getAllRole() {
	    return ResponseEntity.ok(roleService.findAll());
	}
	
}
