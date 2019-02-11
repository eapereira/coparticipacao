package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ViewDestinationDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ViewDestinationEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class ViewDestinationDaoImpl extends
		AbstractDaoImpl<ViewDestinationEntity> implements ViewDestinationDao {

	private static final Logger LOGGER = LogManager
			.getLogger(ViewDestinationDaoImpl.class);

	public ViewDestinationDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
