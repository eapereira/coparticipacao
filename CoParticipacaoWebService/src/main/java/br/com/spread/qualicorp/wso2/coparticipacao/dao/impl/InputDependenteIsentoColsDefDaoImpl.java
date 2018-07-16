package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputDependenteIsentoColsDefDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputDependenteIsentoColsDefEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class InputDependenteIsentoColsDefDaoImpl extends AbstractDaoImpl<InputDependenteIsentoColsDefEntity>
		implements
		InputDependenteIsentoColsDefDao {

	public InputDependenteIsentoColsDefDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
