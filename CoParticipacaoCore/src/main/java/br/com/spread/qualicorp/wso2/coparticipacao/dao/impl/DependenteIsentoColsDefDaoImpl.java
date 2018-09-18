package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DependenteIsentoColsDefDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteIsentoColsDefEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class DependenteIsentoColsDefDaoImpl extends AbstractDaoImpl<DependenteIsentoColsDefEntity>
		implements
		DependenteIsentoColsDefDao {

	public DependenteIsentoColsDefDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}