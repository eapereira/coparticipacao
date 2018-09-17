package br.com.spread.qualicorp.coparticipacao.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.UserRole;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RoleUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.UserUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class UserDetailsImpl implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5472969932877944147L;

	private UserUi userUi;

	public UserDetailsImpl(UserUi userUi) {
		this.userUi = userUi;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		GrantedAuthority grantedAuthority;

		for (UserRole userRole : userUi.getUserRoles()) {
			grantedAuthority = new GrantedAuthorityImpl((RoleUi) userRole.getRole());

			grantedAuthorities.add(grantedAuthority);
		}

		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return userUi.getPasswd();
	}

	@Override
	public String getUsername() {
		return userUi.getNameLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
