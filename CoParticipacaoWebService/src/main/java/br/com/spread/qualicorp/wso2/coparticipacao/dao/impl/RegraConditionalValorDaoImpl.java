package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraConditionalValorDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraConditionalValorEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class RegraConditionalValorDaoImpl
		extends AbstractDaoImpl<RegraConditionalValorEntity>
		implements RegraConditionalValorDao {

	public RegraConditionalValorDaoImpl() throws DaoException {
		super();
	}

}
