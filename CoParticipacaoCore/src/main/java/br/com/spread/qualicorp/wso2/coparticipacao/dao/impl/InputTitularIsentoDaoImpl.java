package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputTitularIsentoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputTitularIsentoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class InputTitularIsentoDaoImpl
		extends AbstractDaoImpl<InputTitularIsentoEntity>
		implements InputTitularIsentoDao {

	public InputTitularIsentoDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
