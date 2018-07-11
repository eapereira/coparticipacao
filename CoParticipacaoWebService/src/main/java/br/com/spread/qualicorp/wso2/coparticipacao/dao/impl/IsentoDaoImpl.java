package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.IsentoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.IsentoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class IsentoDaoImpl extends AbstractDaoImpl<IsentoEntity>
		implements IsentoDao {

	public IsentoDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
