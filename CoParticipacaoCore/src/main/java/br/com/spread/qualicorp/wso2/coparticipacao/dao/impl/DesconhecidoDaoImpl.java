package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DesconhecidoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DesconhecidoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class DesconhecidoDaoImpl extends AbstractDaoImpl<DesconhecidoEntity> implements DesconhecidoDao {

	private static final Logger LOGGER = LogManager.getLogger(DesconhecidoDaoImpl.class);

	public DesconhecidoDaoImpl() throws DaoException {
		super();
	}

	public void deleteByMesAndAno(Long arquivoInputId, int mes, int ano) throws DaoException {
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("deleteByMesAndAno");
			query.setParameter("contratoId", arquivoInputId);
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			query.executeUpdate();

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}

	}

	public List<DesconhecidoEntity> listByMesAndAno(Long contratoId, int mes, int ano) throws DaoException {
		Query query;
		List<DesconhecidoEntity> desconhecidoEntities;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByMesAndAno");
			query.setParameter("contratoId", contratoId);
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			desconhecidoEntities = query.getResultList();

			LOGGER.info("END");
			return desconhecidoEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<DesconhecidoEntity> listByContratoId(Long contratoId) throws DaoException {
		Query query;
		List<DesconhecidoEntity> desconhecidoEntities;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByContratoId");
			query.setParameter("contratoId", contratoId);

			desconhecidoEntities = query.getResultList();

			LOGGER.info("END");
			return desconhecidoEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<DesconhecidoEntity> listByEmpresaId(Long empresaId) throws DaoException {
		Query query;
		List<DesconhecidoEntity> desconhecidoEntities;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByEmpresaId");
			query.setParameter("empresaId", empresaId);

			desconhecidoEntities = query.getResultList();

			LOGGER.info("END");
			return desconhecidoEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<DesconhecidoEntity> listByEmpresaIdAndUseType(Long empresaId, UseType useType) throws DaoException {
		Query query;
		List<DesconhecidoEntity> desconhecidoEntities;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByEmpresaIdAndUseType");
			query.setParameter("empresaId", empresaId);
			query.setParameter("useType", useType);

			desconhecidoEntities = query.getResultList();

			LOGGER.info("END");
			return desconhecidoEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public void deleteByContrato(Long contratoId) throws DaoException {
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("deleteByContrato");
			query.setParameter("contratoId", contratoId);

			query.executeUpdate();

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
