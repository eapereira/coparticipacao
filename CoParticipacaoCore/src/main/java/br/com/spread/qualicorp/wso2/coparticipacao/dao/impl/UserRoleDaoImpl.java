package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.UserRoleDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.UserRoleEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class UserRoleDaoImpl extends AbstractDaoImpl<UserRoleEntity> implements UserRoleDao {

	private static final Logger LOGGER = LogManager.getLogger(UserRoleDaoImpl.class);

	public UserRoleDaoImpl() throws DaoException {
		super();
	}
}
