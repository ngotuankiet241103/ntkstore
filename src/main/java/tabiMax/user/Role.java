package tabiMax.user;

import static tabiMax.user.Permission.ADMIN_CREATE;
import static tabiMax.user.Permission.ADMIN_DELETE;
import static tabiMax.user.Permission.ADMIN_READ;
import static tabiMax.user.Permission.ADMIN_UPDATE;
import static tabiMax.user.Permission.MANAGER_CREATE;
import static tabiMax.user.Permission.MANAGER_DELETE;
import static tabiMax.user.Permission.MANAGER_READ;
import static tabiMax.user.Permission.MANAGER_UPDATE;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {

	USER(Collections.EMPTY_SET), ADMIN(Set.of(MANAGER_READ, MANAGER_UPDATE, MANAGER_DELETE, MANAGER_CREATE)),
	MANAGER(Set.of(MANAGER_READ, MANAGER_UPDATE, MANAGER_DELETE, MANAGER_CREATE))

	;

	private final Set<Permission> permissions;

	public Set<Permission> getPermissions() {
		return permissions;
	}

	Role(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public static Role forName(String roleName) {
		if (roleName == null) {
			throw new IllegalArgumentException("Role name must not be null");
		}

		Role role = valueOf(roleName);
		if (role == null) {
			throw new IllegalArgumentException("Invalid role name: " + roleName);
		}

		return role;
	}

	public Set<SimpleGrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		System.out.println(Role.ADMIN.getPermissions());
		for (Permission permission : getPermissions()) {
			System.out.println(permission.getPermission());
			authorities.add(new SimpleGrantedAuthority(permission.getPermission()));
		}
		authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return authorities;
	}

}