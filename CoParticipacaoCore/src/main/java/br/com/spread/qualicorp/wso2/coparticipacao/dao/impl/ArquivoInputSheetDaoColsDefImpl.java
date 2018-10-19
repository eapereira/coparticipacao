package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoInputSheetColsDefDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputSheetColsDefEntity;

/**
 * 
 * @author edson.apereira
 *
 */
@Repository
public class ArquivoInputSheetDaoColsDefImpl extends AbstractDaoImpl<ArquivoInputSheetColsDefEntity>
		implements ArquivoInputSheetColsDefDao {

	private static final Logger LOGGER = LogManager.getLogger(ArquivoInputSheetDaoColsDefImpl.class);

	public ArquivoInputSheetDaoColsDefImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

}
