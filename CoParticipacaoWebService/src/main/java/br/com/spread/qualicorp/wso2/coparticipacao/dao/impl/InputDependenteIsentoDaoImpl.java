package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputDependenteIsentoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputDependenteIsentoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class InputDependenteIsentoDaoImpl extends AbstractDaoImpl<InputDependenteIsentoEntity>
		implements
		InputDependenteIsentoDao {

	public InputDependenteIsentoDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
