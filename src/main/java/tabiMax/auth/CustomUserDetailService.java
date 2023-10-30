package tabiMax.auth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



import tabiMax.entity.RoleEntity;
import tabiMax.entity.UserEntity;
import tabiMax.repository.IUserRepository;
import tabiMax.user.Permission;
import tabiMax.user.Role;

import static tabiMax.user.Role.*;
@Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	private IUserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			UserEntity userEntity = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("user is not found"));
			Role role = Role.forName("ADMIN");
			System.out.println(Role.MANAGER.getAuthorities());
			Set<SimpleGrantedAuthority> authorities = Role.forName(userEntity.getRole().getName()).getAuthorities();
//			authorities.add(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().getName()));
			CustomUserDetails myUser = new CustomUserDetails(userEntity.getId(), userEntity.getEmail(), userEntity.getPassword(), authorities, 
								true, true, true, userEntity.isEnabled(),userEntity.getFullName(),userEntity.getImage());
			return myUser;
	}

}
