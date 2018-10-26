package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.LancamentoInputSheetColsDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoInputSheetColsEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class LancamentoInputSheetColsDaoImpl extends AbstractDaoImpl<LancamentoInputSheetColsEntity>
		implements LancamentoInputSheetColsDao {

	private static final Logger LOGGER = LogManager.getLogger(LancamentoInputSheetColsDaoImpl.class);

	public LancamentoInputSheetColsDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
