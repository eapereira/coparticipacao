package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraOperationDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraOperationEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class RegraOperationDaoImpl extends AbstractDaoImpl<RegraOperationEntity>
		implements RegraOperationDao {

	public RegraOperationDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
