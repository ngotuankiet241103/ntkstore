package tabiMax.service;

import java.util.List;

import tabiMax.dto.RoleDTO;
import tabiMax.entity.RoleEntity;

public interface IRoleService {
	List<RoleDTO> findAll();
}
