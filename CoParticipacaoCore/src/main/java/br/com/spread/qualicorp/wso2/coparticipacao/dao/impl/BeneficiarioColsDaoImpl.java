package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.BeneficiarioColsDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.BeneficiarioColsEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class BeneficiarioColsDaoImpl extends AbstractDaoImpl<BeneficiarioColsEntity> implements BeneficiarioColsDao {
	private static final Logger LOGGER = LogManager.getLogger(BeneficiarioColsDaoImpl.class);

	public BeneficiarioColsDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<BeneficiarioColsEntity> listByArquivoInputId(Long arquivoInputId) throws DaoException {
		List<BeneficiarioColsEntity> entities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByArquivoInputId");
			query.setParameter("arquivoInputId", arquivoInputId);

			entities = query.getResultList();

			LOGGER.info("END");
			return entities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<BeneficiarioColsEntity> listByArquivoInputSheetId(Long arquivoInputSheetId) throws DaoException {
		List<BeneficiarioColsEntity> entities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByArquivoInputSheetId");
			query.setParameter("arquivoInputSheetId", arquivoInputSheetId);

			entities = query.getResultList();

			LOGGER.info("END");
			return entities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
