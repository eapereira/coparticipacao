package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputLancamentoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputLancamentoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class InputLancamentoDaoImpl extends
		AbstractDaoImpl<InputLancamentoEntity> implements InputLancamentoDao {

	private static final Logger LOGGER = LogManager
			.getLogger(InputLancamentoDaoImpl.class);

	public InputLancamentoDaoImpl() throws DaoException {
		super();
	}

	public List<InputLancamentoEntity> listByArquivoInputColsDefId(Long id)
			throws DaoException {
		Query query;
		StringBuilder sb;
		List<InputLancamentoEntity> inputLancamentoEntities;

		try {
			LOGGER.info("BEGIN");

			sb = new StringBuilder();
			sb.append("select entity from InputLancamentoEntity entity ");
			sb.append(
					"join fetch entity.arquivoInputColsDef arquivoInputColsDef ");
			sb.append("join fetch entity.lancamentoColsDef lancamentoColsDef ");
			sb.append("where arquivoInputColsDef.id = :arquivoInputColsDefId ");
			sb.append("order by arquivoInputColsDef.ordem ");

			query = createQuery(sb.toString());
			query.setParameter("arquivoInputColsDefId", id);

			inputLancamentoEntities = query.getResultList();

			LOGGER.info("END");
			return inputLancamentoEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<InputLancamentoEntity> listByArquivoInputId(Long id)
			throws DaoException {
		Query query;
		StringBuilder sb;
		List<InputLancamentoEntity> inputLancamentoEntities;

		try {
			LOGGER.info("BEGIN");

			sb = new StringBuilder();
			sb.append("select entity from InputLancamentoEntity entity ");
			sb.append(
					"join fetch entity.arquivoInputColsDef arquivoInputColsDef ");
			sb.append("join fetch entity.lancamentoColsDef lancamentoColsDef ");
			sb.append(
					"join fetch arquivoInputColsDef.arquivoInput arquivoInput ");
			sb.append("where arquivoInput.id = :arquivoInputId ");
			sb.append("order by arquivoInputColsDef.ordem ");

			query = createQuery(sb.toString());
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
