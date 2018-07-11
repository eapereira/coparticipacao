package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraConditionalResultDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraConditionalResultEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class RegraConditionalResultDaoImpl
		extends AbstractDaoImpl<RegraConditionalResultEntity>
		implements RegraConditionalResultDao {

	public RegraConditionalResultDaoImpl() throws DaoException {
		super();
	}

}
