package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.BeneficiarioColsDefDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.BeneficiarioColsDefEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class BeneficiarioColsDefDaoImpl
		extends AbstractDaoImpl<BeneficiarioColsDefEntity>
		implements BeneficiarioColsDefDao {

	public BeneficiarioColsDefDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
