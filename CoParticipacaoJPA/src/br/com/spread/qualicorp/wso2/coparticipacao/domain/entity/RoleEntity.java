package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Role;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UserRole;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_ROLE")
@NamedQuery(name = "RoleEntity.findAll", query = "SELECT l FROM RoleEntity l")
public class RoleEntity extends Role {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3288238254624813687L;

	public RoleEntity() {
		super();
	}

	@Column(name = "NM_ROLE")
	@Override
	public String getNameRole() {
		// TODO Auto-generated method stub
		return super.getNameRole();
	}

	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "role",
			targetEntity = UserRoleEntity.class)
	@Override
	public List<UserRole> getUserRoles() {
		// TODO Auto-generated method stub
		return super.getUserRoles();
	}
}
