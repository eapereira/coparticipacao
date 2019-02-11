package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RoleDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RoleEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class RoleDaoImpl extends AbstractDaoImpl<RoleEntity> implements RoleDao {

	private static final Logger LOGGER = LogManager.getLogger(RoleDaoImpl.class);

	public RoleDaoImpl() throws DaoException {
		super();
	}
}
