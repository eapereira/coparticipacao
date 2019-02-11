package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Role;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.User;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UserRole;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_USER_ROLE")
@NamedQuery(name = "UserRoleEntity.findAll", query = "SELECT l FROM UserRoleEntity l")
public class UserRoleEntity extends UserRole implements DomainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2712328377511123018L;

	public UserRoleEntity() {
		super();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "ID_USER")
	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return super.getUser();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = RoleEntity.class)
	@JoinColumn(name = "ID_ROLE")
	@Override
	public Role getRole() {
		// TODO Auto-generated method stub
		return super.getRole();
	}
}
