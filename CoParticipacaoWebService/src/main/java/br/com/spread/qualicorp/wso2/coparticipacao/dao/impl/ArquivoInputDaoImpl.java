package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoInputDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class ArquivoInputDaoImpl extends AbstractDaoImpl<ArquivoInputEntity> implements ArquivoInputDao {

	private static final Logger LOGGER = LogManager.getLogger(ArquivoInputDaoImpl.class);

	public ArquivoInputDaoImpl() throws DaoException {
		super();
	}

	public ArquivoInputEntity findByContrato(Long contratoId) throws DaoException {
		ArquivoInputEntity arquivoInputEntity;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("findByContratoId");
			query.setParameter("contratoId", contratoId);

			arquivoInputEntity = (ArquivoInputEntity) query.getSingleResult();

			LOGGER.info("END");
			return arquivoInputEntity;
		} catch (NoResultException e) {
			LOGGER.info(e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public List<ArquivoInputEntity> listAll() throws DaoException {
		List<ArquivoInputEntity> arquivoInputEntities;
		Query query;
		StringBuilder sb;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listAll");

			arquivoInputEntities = query.getResultList();

			LOGGER.info("END");
			return arquivoInputEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
