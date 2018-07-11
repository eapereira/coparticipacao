package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoOutputSheetColsDefDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputSheetColsDefEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class ArquivoOutputSheetColsDefDaoImpl
		extends AbstractDaoImpl<ArquivoOutputSheetColsDefEntity>
		implements ArquivoOutputSheetColsDefDao {

	public ArquivoOutputSheetColsDefDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
