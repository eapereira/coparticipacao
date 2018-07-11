package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.LancamentoDetailDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoDetailEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class LancamentoDetailDaoImpl extends
		AbstractDaoImpl<LancamentoDetailEntity> implements LancamentoDetailDao {

	public LancamentoDetailDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
