package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.EmpresaDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.EmpresaEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class EmpresaDaoImpl extends AbstractDaoImpl<EmpresaEntity> implements EmpresaDao {

	private static final Logger LOGGER = LogManager.getLogger(EmpresaDaoImpl.class);

	public EmpresaDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmpresaEntity findByName(String nameEmpresa) throws DaoException {
		EmpresaEntity empresaEntity;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("findByName");
			query.setParameter("nameEmpresa", nameEmpresa);

			empresaEntity = (EmpresaEntity) query.getSingleResult();

			LOGGER.info("END");
			return empresaEntity;
		} catch (NoResultException e) {
			LOGGER.info(e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}

	}

	public EmpresaEntity findByCdEmpresa(String cdEmpresa) throws DaoException {
		EmpresaEntity empresaEntity;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("findByCdEmpresa");
			query.setParameter("cdEmpresa", cdEmpresa);

			empresaEntity = (EmpresaEntity) query.getSingleResult();

			LOGGER.info("END");
			return empresaEntity;
		} catch (NoResultException e) {
			LOGGER.info(e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}

	}

	public List<EmpresaEntity> listByOperadoraId(Long operadoraId) throws DaoException {
		List<EmpresaEntity> empresaEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByOperadoraId");
			query.setParameter("operadoraId", operadoraId);

			empresaEntities = query.getResultList();

			LOGGER.info("END");
			return empresaEntities;
		} catch (NoResultException e) {
			LOGGER.info(e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

}
