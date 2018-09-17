package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.ContratoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ContratoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class ContratoDaoImpl extends AbstractDaoImpl<ContratoEntity> implements ContratoDao {

	private static final Logger LOGGER = LogManager.getLogger(ContratoDaoImpl.class);

	public ContratoDaoImpl() throws DaoException {
		super();
	}

	public ContratoEntity findByCdContrato(String cdContrato) throws DaoException {
		ContratoEntity contratoEntity;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("findByCdContrato");
			query.setParameter("cdContrato", cdContrato);

			contratoEntity = (ContratoEntity) query.getSingleResult();

			LOGGER.info("END");
			return contratoEntity;
		} catch (NoResultException e) {
			LOGGER.info(e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}
	
	public ContratoEntity findByCdEmpresaAndCdContrato(String cdEmpresa, String cdContrato) throws DaoException {
		ContratoEntity contratoEntity;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("findByCdEmpresaAndCdContrato");
			query.setParameter("cdEmpresa", cdEmpresa);
			query.setParameter("cdContrato", cdContrato);

			contratoEntity = (ContratoEntity) query.getSingleResult();

			LOGGER.info("END");
			return contratoEntity;
		} catch (NoResultException e) {
			LOGGER.info(e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<ContratoEntity> listByEmpresaId(Long id) throws DaoException {
		Query query;
		List<ContratoEntity> contratoEntities;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByEmpresaId");
			query.setParameter("empresaId", id);

			contratoEntities = query.getResultList();

			LOGGER.info("END");
			return contratoEntities;
		} catch (NoResultException e) {
			LOGGER.info(e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}

	}

}
