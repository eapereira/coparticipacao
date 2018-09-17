package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class Role extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6117937370182697164L;

	private String nameRole;

	private List<UserRole> userRoles;

	public Role() {
		userRoles = new ArrayList<>();
	}

	public String getNameRole() {
		return nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public void addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setRole(this);
	}

	public void removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setRole(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nameRole == null) ? 0 : nameRole.hashCode());
		result = prime * result + ((userRoles == null) ? 0 : userRoles.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (nameRole == null) {
			if (other.nameRole != null)
				return false;
		} else if (!nameRole.equals(other.nameRole))
			return false;
		if (userRoles == null) {
			if (other.userRoles != null)
				return false;
		} else if (!userRoles.equals(other.userRoles))
			return false;
		return true;
	}
}
