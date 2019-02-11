package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.OperadoraDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.OperadoraEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class OperadoraDaoImpl extends AbstractDaoImpl<OperadoraEntity> implements OperadoraDao {

	private static final Logger LOGGER = LogManager.getLogger(OperadoraDaoImpl.class);

	public OperadoraDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<OperadoraEntity> listAll() throws DaoException {
		List<OperadoraEntity> operadoraEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listAll");

			operadoraEntities = query.getResultList();

			LOGGER.info("END");
			return operadoraEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
