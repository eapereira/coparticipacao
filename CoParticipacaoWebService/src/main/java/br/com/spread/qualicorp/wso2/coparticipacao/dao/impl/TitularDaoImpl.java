package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.TitularDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class TitularDaoImpl extends AbstractDaoImpl<TitularEntity> implements TitularDao {

	private static final Logger LOGGER = LogManager.getLogger(TitularDaoImpl.class);

	public TitularDaoImpl() throws DaoException {
		super();
	}

	public TitularEntity findByCpf(String cpf) throws DaoException {
		TitularEntity TitularEntity;
		Query query;

		try {
			LOGGER.info("BEGIN");
			query = createQuery("findByCpf");
			query.setParameter("cpf", cpf);

			TitularEntity = (TitularEntity) query.getSingleResult();

			LOGGER.info("END");
			return TitularEntity;
		} catch (NoResultException e) {
			LOGGER.info(e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<TitularEntity> listByEmpresaId(Long id) throws DaoException {
		List<TitularEntity> titularEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByEmpresaId");
			query.setParameter("empresaId", id);

			titularEntities = query.getResultList();

			LOGGER.info("END");
			return titularEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<TitularEntity> listByEmpresaIdOrderByCpfAndName(Long empresaId) throws DaoException {
		List<TitularEntity> titularEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByEmpresaIdOrderByCpfAndName");
			query.setParameter("empresaId", empresaId);

			titularEntities = query.getResultList();

			LOGGER.info("END");
			return titularEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<TitularEntity> listByEmpresaIdOrderByMatriculaAndName(Long empresaId) throws DaoException {
		List<TitularEntity> titularEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByEmpresaIdOrderByMatriculaAndName");
			query.setParameter("empresaId", empresaId);

			titularEntities = query.getResultList();

			LOGGER.info("END");
			return titularEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}
	
}
