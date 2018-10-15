package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoInputColsDefDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputColsDefEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class ArquivoInputColsDefDaoImpl extends AbstractDaoImpl<ArquivoInputColsDefEntity>
		implements ArquivoInputColsDefDao {
	private static final Logger LOGGER = LogManager.getLogger(ArquivoInputColsDefDaoImpl.class);

	public ArquivoInputColsDefDaoImpl() throws DaoException {
		super();
	}

	public List<ArquivoInputColsDefEntity> listByArquivoInput(Long arquivoInputId) throws DaoException {
		List<ArquivoInputColsDefEntity> arquivoInputColsDefEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByArquivoInputId");
			query.setParameter("arquivoInputId", arquivoInputId);

			arquivoInputColsDefEntities = query.getResultList();

			LOGGER.info("END");
			return arquivoInputColsDefEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}
}
