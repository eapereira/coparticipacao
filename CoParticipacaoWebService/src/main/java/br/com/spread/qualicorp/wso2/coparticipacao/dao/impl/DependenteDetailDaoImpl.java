package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DependenteDetailDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteDetailEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class DependenteDetailDaoImpl extends AbstractDaoImpl<DependenteDetailEntity> implements DependenteDetailDao {

	private static final Logger LOGGER = LogManager.getLogger(DependenteDetailDaoImpl.class);

	public DependenteDetailDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
