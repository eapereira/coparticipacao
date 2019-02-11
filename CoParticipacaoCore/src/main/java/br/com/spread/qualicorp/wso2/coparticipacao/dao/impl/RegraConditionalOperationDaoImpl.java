package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraConditionalOperationDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraConditionalOperationEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class RegraConditionalOperationDaoImpl extends AbstractDaoImpl<RegraConditionalOperationEntity>
		implements RegraConditionalOperationDao {

	private static final Logger LOGGER = LogManager.getLogger(RegraConditionalOperationDaoImpl.class);

	public RegraConditionalOperationDaoImpl() throws DaoException {
		super();
	}

	public List<RegraConditionalOperationEntity> listByRegraConditionalId(Long regraConditionalId) throws DaoException {
		List<RegraConditionalOperationEntity> regraConditionalOperationEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");
			query = createQuery("listByRegraConditionalId");
			query.setParameter("regraConditionalId", regraConditionalId);

			regraConditionalOperationEntities = query.getResultList();

			LOGGER.info("END");
			return regraConditionalOperationEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
