package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputDependenteDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputDependenteEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class InputDependenteDaoImpl
		extends AbstractDaoImpl<InputDependenteEntity>
		implements InputDependenteDao {

	private static final Logger LOGGER = LogManager
			.getLogger(InputDependenteDaoImpl.class);

	public List<InputDependenteEntity> listByArquivoInputColsDefId(Long id)
			throws DaoException {
		Query query;
		StringBuilder sb;
		List<InputDependenteEntity> inputDependenteEntities;

		try {
			LOGGER.info("BEGIN");
			sb = new StringBuilder();
			sb.append(
					"select inputDependente from InputDependenteEntity inputDependente ");
			sb.append(
					"join fetch inputDependente.arquivoInputColsDef arquivoInputColsDef ");
			sb.append(
					"join fetch inputDependente.dependenteColsDef dependenteColsDef ");
			sb.append("where arquivoInputColsDef.id = :arquivoInputColsDefId ");

			query = createQuery(sb.toString());
			query.setParameter("arquivoInputColsDefId", id);

			inputDependenteEntities = query.getResultList();

			LOGGER.info("END");
			return inputDependenteEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<InputDependenteEntity> listByArquivoInputId(Long id)
			throws DaoException {
		Query query;
		StringBuilder sb;
		List<InputDependenteEntity> inputDependenteEntities;

		try {
			LOGGER.info("BEGIN");
			sb = new StringBuilder();
			sb.append(
					"select inputDependente from InputDependenteEntity inputDependente ");
			sb.append(
					"join fetch inputDependente.arquivoInputColsDef arquivoInputColsDef ");
			sb.append(
					"join fetch inputDependente.dependenteColsDef dependenteColsDef ");
			sb.append(
					"join fetch arquivoInputColsDef.arquivoInput arquivoInput ");
			sb.append("where arquivoInput.id = :arquivoInputId ");

			query = createQuery(sb.toString());
			query.setParameter("arquivoInputId", id);

			inputDependenteEntities = query.getResultList();

			LOGGER.info("END");
			return inputDependenteEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
