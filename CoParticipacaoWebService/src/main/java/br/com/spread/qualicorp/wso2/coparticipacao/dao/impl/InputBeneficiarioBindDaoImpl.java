package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputBeneficiarioBindDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputBeneficiarioBindEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class InputBeneficiarioBindDaoImpl
		extends AbstractDaoImpl<InputBeneficiarioBindEntity>
		implements InputBeneficiarioBindDao {

	public InputBeneficiarioBindDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
