package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.LancamentoInputColsDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoInputColsEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class LancamentoInputColsDaoImpl extends AbstractDaoImpl<LancamentoInputColsEntity>
		implements LancamentoInputColsDao {

	private static final Logger LOGGER = LogManager.getLogger(LancamentoInputColsDaoImpl.class);

	public LancamentoInputColsDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<LancamentoInputColsEntity> listByLancamentoInputId(Long lancamentoInputId) throws DaoException {
		Query query;
		List<LancamentoInputColsEntity> lancamentoInputColsEntities;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByLancamentoInputId");
			query.setParameter("lancamentoInputId", lancamentoInputId);

			lancamentoInputColsEntities = query.getResultList();

			LOGGER.info("END");
			return lancamentoInputColsEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<LancamentoInputColsEntity> listByArquivoInputId(Long arquivoInputId) throws DaoException {
		Query query;
		List<LancamentoInputColsEntity> lancamentoInputColsEntities;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByArquivoInputId");
			query.setParameter("arquivoInputId", arquivoInputId);

			lancamentoInputColsEntities = query.getResultList();

			LOGGER.info("END");
			return lancamentoInputColsEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
