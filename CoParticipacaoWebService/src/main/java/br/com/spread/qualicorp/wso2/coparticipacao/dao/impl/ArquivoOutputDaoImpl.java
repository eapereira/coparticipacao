package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoOutputDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class ArquivoOutputDaoImpl extends AbstractDaoImpl<ArquivoOutputEntity>
		implements ArquivoOutputDao {

	private static final Logger LOGGER = LogManager
			.getLogger(ArquivoOutputDaoImpl.class);

	public ArquivoOutputDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArquivoOutputEntity findByArquivoInputId(Long id)
			throws DaoException {
		ArquivoOutputEntity arquivoOutputEntity;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery2("findByArquivoInputId");
			query.setParameter("arquivoInputId", id);

			arquivoOutputEntity = (ArquivoOutputEntity) query.getSingleResult();

			LOGGER.info("END");
			return arquivoOutputEntity;
		} catch (NoResultException e) {
			LOGGER.info(e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

}
