package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.OperadoraDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.OperadoraEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class OperadoraDaoImpl extends AbstractDaoImpl<OperadoraEntity>
		implements OperadoraDao {

	public OperadoraDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
