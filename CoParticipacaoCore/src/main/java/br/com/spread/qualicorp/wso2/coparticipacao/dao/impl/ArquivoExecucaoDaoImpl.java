package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoExecucaoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoExecucaoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class ArquivoExecucaoDaoImpl extends AbstractDaoImpl<ArquivoExecucaoEntity> implements ArquivoExecucaoDao {

	private static final Logger LOGGER = LogManager.getLogger(ArquivoExecucaoDaoImpl.class);

	public ArquivoExecucaoDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<ArquivoExecucaoEntity> listByEmpresaIdAndMesAndAno(Long empresaId, Integer mes, Integer ano)
			throws DaoException {
		List<ArquivoExecucaoEntity> arquivoExecucaoEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByEmpresaIdAndMesAndAno");
			query.setParameter("empresaId", empresaId);
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			arquivoExecucaoEntities = query.getResultList();

			LOGGER.info("END");
			return arquivoExecucaoEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public ArquivoExecucaoEntity findByArquivoInputIdAndMesAndAno(Long arquivoInputId, Integer mes, Integer ano)
			throws DaoException {
		ArquivoExecucaoEntity arquivoExecucaoEntity;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("findByArquivoInputIdAndMesAndAno");
			query.setParameter("arquivoInputId", arquivoInputId);
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			arquivoExecucaoEntity = (ArquivoExecucaoEntity) query.getSingleResult();

			LOGGER.info("END");
			return arquivoExecucaoEntity;
		} catch (NoResultException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public ArquivoExecucaoEntity findByContratoIdAndMesAndAno(Long contratoId, Integer mes, Integer ano)
			throws DaoException {
		ArquivoExecucaoEntity arquivoExecucaoEntity;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("findByContratoIdAndMesAndAno");
			query.setParameter("contratoId", contratoId);
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			arquivoExecucaoEntity = (ArquivoExecucaoEntity) query.getSingleResult();

			LOGGER.info("END");
			return arquivoExecucaoEntity;
		} catch (NoResultException e) {
			LOGGER.error(e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public void deleteByContratoIdAndMesAndAno(Long contratoId, Integer mes, Integer ano) throws DaoException {
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("deleteByContratoIdAndMesAndAno");
			query.setParameter("contratoId", contratoId);
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			query.executeUpdate();

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public void deleteByEmpresaIdAndMesAndAno(Long empresaId, Integer mes, Integer ano) throws DaoException {
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("deleteByEmpresaIdAndMesAndAno");
			query.setParameter("empresaId", empresaId);
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			query.executeUpdate();

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

}
