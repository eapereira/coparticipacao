package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

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

	public ViewDestinationDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
