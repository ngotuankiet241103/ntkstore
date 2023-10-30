package tabiMax.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import tabiMax.auth.AuthenticationRequest;
import tabiMax.auth.AuthenticationResponse;
import tabiMax.auth.PasswordUpdateRequest;
import tabiMax.composite.UserRequestUpdate;
import tabiMax.dto.UserDTO;
import tabiMax.entity.UserEntity;


public interface IUserService {
	String signUpUser(UserEntity appUser,String token);
	
	UserEntity findById(Long id);
	
	List<UserEntity> findUserByRoleId(Long id);

	void save(UserDTO user);
	UserEntity findByEmail(String email);

	UserEntity findByFullName(String fullName);

	UserEntity updateUserById(PasswordUpdateRequest user, Long id);

	AuthenticationResponse authenticate(AuthenticationRequest usera);

	AuthenticationResponse refeshToken(String string);

	void enableAppUser(String email);

	boolean existsByEmail(String email);

	Map<String, Object> findAll(Pageable pageable);

	void createAdmin();

	Object updateUserByRole(UserRequestUpdate user);

	Object updateEnableUser(long id);

	Object deleteUserById(long id);

	

	
}
