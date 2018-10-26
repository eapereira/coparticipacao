package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.LancamentoInputSheetDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoInputSheetEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class LancamentoInputSheetDaoImpl extends AbstractDaoImpl<LancamentoInputSheetEntity>
		implements LancamentoInputSheetDao {

	private static final Logger LOGGER = LogManager.getLogger(LancamentoInputSheetDaoImpl.class);

	public LancamentoInputSheetDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
