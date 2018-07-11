package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DependenteIsentoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteIsentoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class DependenteIsentoDaoImpl extends
		AbstractDaoImpl<DependenteIsentoEntity> implements DependenteIsentoDao {

	public DependenteIsentoDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
