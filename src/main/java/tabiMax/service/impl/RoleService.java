package tabiMax.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tabiMax.dto.RoleDTO;
import tabiMax.entity.RoleEntity;
import tabiMax.modelMapper.modelMapper;
import tabiMax.repository.IRoleRepository;
import tabiMax.service.IRoleService;

@Service
public class RoleService implements IRoleService {
	private IRoleRepository roleRepository;

	@Autowired
	public RoleService(IRoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public List<RoleDTO> findAll() {

		return roleRepository.findAll().stream().map(role -> modelMapper.toMapper().map(role, RoleDTO.class))
				.collect(Collectors.toList());
	}

}
