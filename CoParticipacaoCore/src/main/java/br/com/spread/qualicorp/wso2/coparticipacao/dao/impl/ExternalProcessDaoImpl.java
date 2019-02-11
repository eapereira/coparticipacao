package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ExternalProcessDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ExternalProcessEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class ExternalProcessDaoImpl extends AbstractDaoImpl<ExternalProcessEntity> implements ExternalProcessDao {

	private static final Logger LOGGER = LogManager.getLogger(ExternalProcessDaoImpl.class);

	public ExternalProcessDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
