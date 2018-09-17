package br.com.spread.qualicorp.coparticipacao.security;

import org.springframework.security.core.GrantedAuthority;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RoleUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class GrantedAuthorityImpl implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4062438198154319621L;

	private RoleUi roleUi;

	public GrantedAuthorityImpl(RoleUi roleUi) {
		this.roleUi = roleUi;
	}

	@Override
	public String getAuthority() {
		return roleUi.getNameRole();
	}

}
