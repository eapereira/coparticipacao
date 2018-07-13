package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.BeneficiarioDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.BeneficiarioEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class BeneficiarioDaoImpl extends AbstractDaoImpl<BeneficiarioEntity>
		implements BeneficiarioDao {

	public BeneficiarioDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
