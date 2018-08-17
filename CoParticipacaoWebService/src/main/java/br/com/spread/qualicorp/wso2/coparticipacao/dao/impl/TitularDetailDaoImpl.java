package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.TitularDetailDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularDetailEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class TitularDetailDaoImpl extends AbstractDaoImpl<TitularDetailEntity> implements TitularDetailDao {

	private static final Logger LOGGER = LogManager.getLogger(TitularDetailDaoImpl.class);

	public TitularDetailDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
