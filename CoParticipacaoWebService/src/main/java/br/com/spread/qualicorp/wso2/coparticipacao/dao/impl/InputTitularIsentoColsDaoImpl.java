package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputTitularIsentoColsDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputTitularIsentoColsEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class InputTitularIsentoColsDaoImpl extends AbstractDaoImpl<InputTitularIsentoColsEntity>
		implements
		InputTitularIsentoColsDao {

	public InputTitularIsentoColsDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
