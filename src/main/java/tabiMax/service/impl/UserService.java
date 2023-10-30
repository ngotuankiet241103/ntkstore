package tabiMax.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import redis.clients.jedis.Jedis;
import tabiMax.auth.AuthenticationRequest;
import tabiMax.auth.AuthenticationResponse;
import tabiMax.auth.CustomUserDetailService;
import tabiMax.auth.CustomUserDetails;
import tabiMax.auth.PasswordUpdateRequest;
import tabiMax.composite.UserRequestUpdate;
import tabiMax.config.JwtService;
import tabiMax.config.RedisCache;
import tabiMax.dto.UserDTO;
import tabiMax.email.EmailSender;
import tabiMax.entity.ConfirmationToken;
import tabiMax.entity.RoleEntity;
import tabiMax.entity.UserEntity;
import tabiMax.modelMapper.modelMapper;
import tabiMax.paging.pageRequest;
import tabiMax.register.ConfirmationTokenService;
import tabiMax.repository.IRoleRepository;
import tabiMax.repository.IUserRepository;
import tabiMax.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private CustomUserDetailService userDetailsService;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private PasswordEncoder passwordEncoder;
//	@Qualifier("userRepository")
	private IUserRepository userRepository;
	@Autowired
	private UserDetailsService userDetailsSerive;
	@Autowired
	private ConfirmationTokenService confirmationTokenService;
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private IRoleRepository roleRepository;
	@Autowired
	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserEntity findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public Map<String, Object> findAll(Pageable pageable) {
		Page<UserEntity> page = userRepository.findAll(pageable);
		List<UserDTO> user = page.getContent().stream().map(userEntity -> {
			UserDTO userDTO = modelMapper.toMapper().map(userEntity, UserDTO.class);
			userDTO.setRole(List.of(userEntity.getRole().getName()));
			return userDTO;
		}).collect(Collectors.toList());
		pageRequest paging = new pageRequest(page.getNumber(), page.getTotalPages());
		Map<String, Object> response = new HashMap<>();
		response.put("users", user);
		response.put("paging", paging);
		return response;
	}

	

	@Override
	public List<UserEntity> findUserByRoleId(Long id) {

		return userRepository.findUserByRoleId(id);
	}

	@Override
	public void save(UserDTO user) {
		RoleEntity role_user = new RoleEntity();
		role_user.setCode("nguoi-dung");
		role_user.setName("ADMIN");
		UserEntity userEntity = modelMapper.toMapper().map(user, UserEntity.class);
		Set<RoleEntity> roles = new HashSet<>();
		roles.add(role_user);
		userEntity.setRole(role_user);
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

		userRepository.save(userEntity);

	}

	@Override
	public UserEntity findByFullName(String fullName) {
		return userRepository.findByFullName(fullName);
	}

	@Override
	public UserEntity findByEmail(String email) {

		return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("email is not found"));
	}

	@Transactional
	@Override
	public UserEntity updateUserById(PasswordUpdateRequest user, Long id) {
		UserEntity userEntity = userRepository.findById(id);
		if (passwordEncoder.matches(user.getPassword(), userEntity.getPassword())) {
			user.setPasswordNew(passwordEncoder.encode(user.getPasswordNew()));
			userRepository.updateUserById(user.getFullName(), user.getEmail(), user.getPhone(), user.getPasswordNew(),
					id);
			userEntity = userRepository.findById(id);
		} else {
			userEntity = null;
		}
		return userEntity;

	}

	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest usera) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(usera.getEmail(), usera.getPassword()));
		CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(usera.getEmail());
		String jwtToken = jwtService.generateToken(user);
		String refreshToken = jwtService.generateRefreshToken(user);
		AuthenticationResponse response = new AuthenticationResponse();
		System.out.println("jwtToken");
		response.setAccessToken(jwtToken);
		response.setRefreshToken(refreshToken);
		Jedis jedis = RedisCache.jedisPool.getResource();
		jedis.hset("refeshToken", user.getUserId().toString(), refreshToken);
		return response;
	}

	@Override
	public AuthenticationResponse refeshToken(String refeshToken) {
		Jedis jedis = RedisCache.jedisPool.getResource();
		final String jwt;
		final String userEmail;
		String id = "";

		jwt = refeshToken;
		AuthenticationResponse responseToken = null;
		try {
			id = jwtService.extractId(jwt);

		} catch (ExpiredJwtException ex) {
			return responseToken;
		}
		responseToken = new AuthenticationResponse();
		String refeshTokenStore = jedis.hget("refeshToken", id);
		System.out.println(refeshToken);
		if (jwt.equals(refeshTokenStore)) {
			userEmail = jwtService.extractUsername(jwt);
			if (userEmail != null) {
				CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(userEmail);
				if (jwtService.isTokenValid(jwt, user)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null,
							user.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource());
					SecurityContextHolder.getContext().setAuthentication(authToken);
					String jwtToken = jwtService.generateToken(user);

					responseToken.setAccessToken(jwtToken);
					responseToken.setRefreshToken(jwt);
					System.out.println(jwtToken);
//					new ObjectMapper().writeValue(response.getOutputStream(), responseToken);
				}

			}
		}
		return responseToken;
	}

	@Override
	public String signUpUser(UserEntity appUser, String token) {
//		boolean userExists = userRepository.findByEmail(appUser.getEmail()).isPresent();
//
//		if (userExists) {
//			// TODO check of attributes are the same and
//			// TODO if email not confirmed send confirmation email.
//			throw new IllegalStateException("email already taken");
//		}
		String encodedPassword = passwordEncoder.encode(appUser.getPassword());

		appUser.setPassword(encodedPassword);

		userRepository.save(appUser);

		ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15), appUser);

		confirmationTokenService.saveConfirmationToken(confirmationToken);

		return token;
	}

	@Async
	@Override
	public void enableAppUser(String email) {
		UserEntity user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User is not found"));
		userRepository.enableUserEntity(email);

	}

	@Override
	public boolean existsByEmail(String email) {

		return userRepository.existsByEmail(email);
	}

	@Override
	public void createAdmin() {
		UserEntity admin = userRepository.findByEmail("ngotuankiet12347@gmail.com").orElse(null);
		if(admin == null) {
			RoleEntity roleAdmin = roleRepository.findByName("ADMIN");
			if(roleAdmin == null) {
				roleAdmin = new RoleEntity();
				roleAdmin.setCode("quan-tri");
				roleAdmin.setName("ADMIN");
				roleAdmin = roleRepository.save(roleAdmin);
			}
			String encodedPassword = passwordEncoder.encode("ntkstore");
			admin =  new UserEntity("ngotuankiet12347@gmail.com", encodedPassword, "Ngô Tuấn Kiệt", true, roleAdmin);
			userRepository.save(admin);
		}
		createAllRole();
	}
	private void createAllRole() {
		RoleEntity roleManager = roleRepository.findByName("MANAGER");
		
		if(roleManager == null) {
			roleManager = new RoleEntity();
			roleManager.setCode("quan-li");
			roleManager.setName("MANAGER");
			roleRepository.save(roleManager);
		}
	}
	@Override
	public Object updateUserByRole(UserRequestUpdate user) {
		RoleEntity role = roleRepository.findByName(user.getRole());
		userRepository.updateUserByRole(user.getId(), role);
		
		return null;
	}

	@Override
	public Object updateEnableUser(long id) {
		userRepository.updateUserByEnabled(id);
		return null;
	}

	@Override
	public Object deleteUserById(long id) {
		userRepository.deleteUserById(id);
		return null;
	}

}
