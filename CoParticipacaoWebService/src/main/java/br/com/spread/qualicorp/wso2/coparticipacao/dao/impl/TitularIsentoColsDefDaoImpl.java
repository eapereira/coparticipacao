package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.TitularIsentoColsDefDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularIsentoColsDefEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class TitularIsentoColsDefDaoImpl extends AbstractDaoImpl<TitularIsentoColsDefEntity>
		implements
		TitularIsentoColsDefDao {

	public TitularIsentoColsDefDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
