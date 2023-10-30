package tabiMax.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import tabiMax.entity.RoleEntity;

public interface IRoleRepository extends Repository<RoleEntity, Long>{
	List<RoleEntity> findAll();

	RoleEntity save(RoleEntity role_user);

	RoleEntity findByName(String string);
}
