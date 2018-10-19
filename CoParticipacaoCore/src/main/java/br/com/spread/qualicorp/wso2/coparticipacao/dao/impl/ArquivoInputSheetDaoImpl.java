package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoInputSheetDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputSheetEntity;

/**
 * 
 * @author edson.apereira
 *
 */
@Repository
public class ArquivoInputSheetDaoImpl extends AbstractDaoImpl<ArquivoInputSheetEntity> implements ArquivoInputSheetDao {

	private static final Logger LOGGER = LogManager.getLogger(ArquivoInputSheetDaoImpl.class);

	public ArquivoInputSheetDaoImpl() throws DaoException {
		super();
	}

	public List<ArquivoInputSheetEntity> listByArquivoInputId(Long arquivoInputId) throws DaoException {
		List<ArquivoInputSheetEntity> arquivoInputSheetEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByArquivoInputId");
			query.setParameter("arquivoInputId", arquivoInputId);	
			
			arquivoInputSheetEntities = query.getResultList();

			LOGGER.info("END");
			return arquivoInputSheetEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
