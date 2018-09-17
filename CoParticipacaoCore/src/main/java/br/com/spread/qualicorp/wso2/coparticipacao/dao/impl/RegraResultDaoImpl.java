package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraResultDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraResultEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class RegraResultDaoImpl extends AbstractDaoImpl<RegraResultEntity> implements RegraResultDao {

	private static final Logger LOGGER = LogManager.getLogger(RegraResultDaoImpl.class);

	public RegraResultDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<RegraResultEntity> listByRegraId(Long regraId) throws DaoException {
		List<RegraResultEntity> regraResultEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByRegraId");
			query.setParameter("regraId", regraId);

			regraResultEntities = query.getResultList();

			LOGGER.info("END");
			return regraResultEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
