package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputDependenteIsentoColsDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputDependenteIsentoColsEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class InputDependenteIsentoColsDaoImpl extends AbstractDaoImpl<InputDependenteIsentoColsEntity>
		implements
		InputDependenteIsentoColsDao {

	public InputDependenteIsentoColsDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
