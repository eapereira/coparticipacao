package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.LancamentoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class LancamentoDaoImpl extends AbstractDaoImpl<LancamentoEntity> implements LancamentoDao {

	private static final Logger LOGGER = LogManager.getLogger(LancamentoDaoImpl.class);

	public LancamentoDaoImpl() throws DaoException {
		super();
	}

	public void deleteByMesAndAno(Long contratoId, int mes, int ano) throws DaoException {
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("deleteByMesAndAno");
			query.setParameter("contratoId", contratoId);
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			query.executeUpdate();

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}

	}

	public List<LancamentoEntity> listByMesAndAno(Long contratoId, int mes, int ano) throws DaoException {
		List<LancamentoEntity> lancamentoEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByMesAndAno");
			query.setParameter("contratoId", contratoId);
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			lancamentoEntities = query.getResultList();

			LOGGER.info("END");
			return lancamentoEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<LancamentoEntity> listByEmpresaId(Long empresaId) throws DaoException {
		List<LancamentoEntity> lancamentoEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByEmpresaId");
			query.setParameter("empresaId", empresaId);

			lancamentoEntities = query.getResultList();

			LOGGER.info("END");
			return lancamentoEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
