package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputDependenteIsentoColsDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputDependenteIsentoColsEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class InputDependenteIsentoColsDaoImpl
		extends AbstractDaoImpl<InputDependenteIsentoColsEntity>
		implements InputDependenteIsentoColsDao {

	private static final Logger LOGGER = LogManager
			.getLogger(InputDependenteIsentoColsDaoImpl.class);

	public InputDependenteIsentoColsDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<InputDependenteIsentoColsEntity> listByInputDependenteIsentoId(
			Long id) throws DaoException {
		List<InputDependenteIsentoColsEntity> inputDependenteIsentoColsEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByInputDependenteIsentoId");
			query.setParameter("id", id);

			inputDependenteIsentoColsEntities = query.getResultList();

			LOGGER.info("END");
			return inputDependenteIsentoColsEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<InputDependenteIsentoColsEntity> listByArquivoInputId(Long id)
			throws DaoException {
		List<InputDependenteIsentoColsEntity> inputDependenteIsentoColsEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByArquivoInputId");
			query.setParameter("id", id);

			inputDependenteIsentoColsEntities = query.getResultList();

			LOGGER.info("END");
			return inputDependenteIsentoColsEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
