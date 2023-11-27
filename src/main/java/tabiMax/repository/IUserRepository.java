package tabiMax.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import tabiMax.entity.RoleEntity;
import tabiMax.entity.UserEntity;

public interface IUserRepository extends Repository<UserEntity, Long> {
	
	UserEntity findById(Long id);


	UserEntity findByFullName(String fullName);

	Page<UserEntity> findAll(Pageable page);

	List<UserEntity> findUserByRoleId(Long roleId);

	UserEntity save(UserEntity entity);

	@Modifying
	@Query("UPDATE UserEntity c SET c.fullName= ?1, c.email = ?2,c.phone = ?3, c.password = ?4 WHERE c.id = ?5")
	void updateUserById(String fullName, String email, String phone, String passwordNew, Long id);
	@Modifying
	@Query("UPDATE UserEntity c SET c.fullName= ?1, c.email = ?2,c.phone = ?3 WHERE c.id = ?4")
	void updateInfoById(String fullName, String email, String phone, Long id);

	Optional<UserEntity> findByEmail(String email);

	@Transactional
	@Modifying
	@Query("UPDATE UserEntity  a " + "SET a.enabled = TRUE WHERE a.email = ?1")
	int enableUserEntity(String email);


	boolean existsByEmail(String email);

	@Transactional
	@Modifying
	@Query("UPDATE UserEntity  a  SET a.role = ?2 WHERE a.id = ?1")
	void updateUserByRole(long id, RoleEntity role);

	@Transactional
	@Modifying
	@Query("UPDATE UserEntity  a " + "SET a.enabled = FALSE WHERE a.id = ?1")
	void updateUserByEnabled(long id);

	@Transactional
	@Modifying
	@Query("DELETE FROM UserEntity u  WHERE u.id = ?1")
	void deleteUserById(long id);
}
