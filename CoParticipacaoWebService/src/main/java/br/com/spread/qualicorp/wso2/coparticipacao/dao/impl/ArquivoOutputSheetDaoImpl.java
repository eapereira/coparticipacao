package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoOutputSheetDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputSheetEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class ArquivoOutputSheetDaoImpl
		extends AbstractDaoImpl<ArquivoOutputSheetEntity>
		implements ArquivoOutputSheetDao {

	public ArquivoOutputSheetDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
