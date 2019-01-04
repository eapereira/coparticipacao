package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraConditionalResultDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraConditionalResultEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class RegraConditionalResultDaoImpl extends AbstractDaoImpl<RegraConditionalResultEntity>
		implements RegraConditionalResultDao {

	private static final Logger LOGGER = LogManager.getLogger(RegraConditionalResultDaoImpl.class);

	public RegraConditionalResultDaoImpl() throws DaoException {
		super();
	}

	public List<RegraConditionalResultEntity> listByRegraConditionalId(Long regraConditionalId) throws DaoException {
		List<RegraConditionalResultEntity> regraConditionalResultEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");
			query = createQuery("listByRegraConditionalId");
			query.setParameter("regraConditionalId", regraConditionalId);

			regraConditionalResultEntities = query.getResultList();

			LOGGER.info("END");
			return regraConditionalResultEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
