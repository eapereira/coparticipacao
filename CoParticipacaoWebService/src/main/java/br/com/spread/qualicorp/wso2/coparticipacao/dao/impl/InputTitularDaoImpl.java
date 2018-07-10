package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputTitularDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputTitularEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class InputTitularDaoImpl extends AbstractDaoImpl<InputTitularEntity>
		implements InputTitularDao {

	private static final Logger LOGGER = LogManager
			.getLogger(InputTitularDaoImpl.class);

	public List<InputTitularEntity> listByArquivoInputColsDefId(Long id)
			throws DaoException {
		Query query;
		StringBuilder sb;
		List<InputTitularEntity> inputTitularEntities;

		try {
			LOGGER.info("BEGIN");
			sb = new StringBuilder();
			sb.append(
					"select inputTitular from InputTitularEntity inputTitular ");
			sb.append(
					"join fetch inputTitular.arquivoInputColsDef arquivoInputColsDef ");
			sb.append("join fetch inputTitular.titularColsDef titularColsDef ");
			sb.append("where arquivoInputColsDef.id = :arquivoInputColsDefId ");

			query = createQuery(sb.toString());
			query.setParameter("arquivoInputColsDefId", id);

			inputTitularEntities = query.getResultList();

			LOGGER.info("END");
			return inputTitularEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<InputTitularEntity> listByArquivoInputId(Long id)
			throws DaoException {
		Query query;
		StringBuilder sb;
		List<InputTitularEntity> inputTitularEntities;

		try {
			LOGGER.info("BEGIN");
			sb = new StringBuilder();
			sb.append(
					"select inputTitular from InputTitularEntity inputTitular ");
			sb.append(
					"join fetch inputTitular.arquivoInputColsDef arquivoInputColsDef ");
			sb.append("join fetch inputTitular.titularColsDef titularColsDef ");
			sb.append("join fetch arquivoInputColsDef.arquivoInput arquivoInput ");
			sb.append("where arquivoInput.id = :arquivoInputId ");

			query = createQuery(sb.toString());
			query.setParameter("arquivoInputId", id);

			inputTitularEntities = query.getResultList();

			LOGGER.info("END");
			return inputTitularEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
