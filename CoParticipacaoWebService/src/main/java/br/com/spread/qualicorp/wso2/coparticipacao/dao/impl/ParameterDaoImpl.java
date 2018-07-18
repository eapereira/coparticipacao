package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ParameterDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ParameterEntity;

/**
 * 
 * @author <a href="mailto:edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class ParameterDaoImpl extends AbstractDaoImpl<ParameterEntity>
		implements ParameterDao {

	private static final Logger LOGGER = LogManager
			.getLogger(ParameterDaoImpl.class);

	public ParameterDaoImpl() throws DaoException {
		super();
	}

	public List<ParameterEntity> listByEmpresaId(Long id) throws DaoException {
		List<ParameterEntity> parameterEntities;
		Query query;
		StringBuilder sb;

		try {
			LOGGER.info("BEGIN");

			sb = new StringBuilder();
			sb.append("select parameter from ParameterEntity parameter ");
			sb.append("join fetch parameter.empresa empresa ");
			sb.append("where empresa.id = :id ");

			query = createQueryOld(sb.toString());
			query.setParameter("id", id);

			parameterEntities = query.getResultList();
			LOGGER.info("END");
			return parameterEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
