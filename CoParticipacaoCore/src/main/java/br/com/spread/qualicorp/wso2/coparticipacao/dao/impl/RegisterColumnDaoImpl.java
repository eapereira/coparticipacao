package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegisterColumnDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegisterColumnEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class RegisterColumnDaoImpl extends AbstractDaoImpl<RegisterColumnEntity> implements RegisterColumnDao {

	private static final Logger LOGGER = LogManager.getLogger(RegisterColumnDaoImpl.class);

	public RegisterColumnDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<RegisterColumnEntity> listByRegisterId(Long registerId) throws DaoException {
		List<RegisterColumnEntity> registerColumnEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByRegisterId");
			query.setParameter("registerId", registerId);

			registerColumnEntities = query.getResultList();

			LOGGER.info("END");
			return registerColumnEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
