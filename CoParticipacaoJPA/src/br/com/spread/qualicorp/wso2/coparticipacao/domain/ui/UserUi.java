package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.User;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.UserEntity;

/**
 * The persistent class for the tb_user database table.
 * 
 */
public class UserUi extends User implements DomainUi {
	private static final long serialVersionUID = 1L;

	public UserUi() {
	}

	public UserUi(UserEntity entity) {
		super(entity);
	}

}