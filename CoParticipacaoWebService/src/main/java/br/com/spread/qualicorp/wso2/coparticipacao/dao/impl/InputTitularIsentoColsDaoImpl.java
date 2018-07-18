package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputTitularIsentoColsDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputTitularIsentoColsEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class InputTitularIsentoColsDaoImpl
		extends AbstractDaoImpl<InputTitularIsentoColsEntity>
		implements InputTitularIsentoColsDao {

	private static final Logger LOGGER = LogManager
			.getLogger(InputTitularIsentoColsDaoImpl.class);

	public InputTitularIsentoColsDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<InputTitularIsentoColsEntity> listByInputTitularIsentoId(
			Long id) throws DaoException {
		List<InputTitularIsentoColsEntity> inputTitularIsentoColsEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByInputTitularIsentoId");
			query.setParameter("id", id);

			inputTitularIsentoColsEntities = query.getResultList();

			LOGGER.info("END");
			return inputTitularIsentoColsEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<InputTitularIsentoColsEntity> listByArquivoInputId(Long id)
			throws DaoException {
		List<InputTitularIsentoColsEntity> inputTitularIsentoColsEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByArquivoInputId");
			query.setParameter("id", id);

			inputTitularIsentoColsEntities = query.getResultList();

			LOGGER.info("END");
			return inputTitularIsentoColsEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
