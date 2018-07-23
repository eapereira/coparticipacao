package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class ArquivoOutputSheetDaoImpl extends AbstractDaoImpl<ArquivoOutputSheetEntity> implements ArquivoOutputSheetDao {

	private static final Logger LOGGER = LogManager.getLogger(ArquivoOutputSheetDaoImpl.class);

	public ArquivoOutputSheetDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<ArquivoOutputSheetEntity> listByArquivoOutputId(Long id) throws DaoException {
		List<ArquivoOutputSheetEntity> arquivoOutputSheetEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByArquivoOutputId");
			query.setParameter("arquivoOutputId", id);

			arquivoOutputSheetEntities = query.getResultList();

			LOGGER.info("END");
			return arquivoOutputSheetEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
