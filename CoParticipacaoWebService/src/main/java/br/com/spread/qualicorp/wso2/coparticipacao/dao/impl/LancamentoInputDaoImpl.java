package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.LancamentoInputDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoInputEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class LancamentoInputDaoImpl extends
		AbstractDaoImpl<LancamentoInputEntity> implements LancamentoInputDao {

	private static final Logger LOGGER = LogManager
			.getLogger(LancamentoInputDaoImpl.class);

	public LancamentoInputDaoImpl() throws DaoException {
		super();
	}

	public List<LancamentoInputEntity> listByArquivoInputId(Long id)
			throws DaoException {
		Query query;
		List<LancamentoInputEntity> inputLancamentoEntities;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByArquivoInputId");
			query.setParameter("arquivoInputId", id);

			inputLancamentoEntities = query.getResultList();

			LOGGER.info("END");
			return inputLancamentoEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
