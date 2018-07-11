package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraValorDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraValorEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class RegraValorDaoImpl extends AbstractDaoImpl<RegraValorEntity>
		implements RegraValorDao {

	public RegraValorDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
