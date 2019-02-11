package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraConditionalValorDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraConditionalValorEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class RegraConditionalValorDaoImpl extends AbstractDaoImpl<RegraConditionalValorEntity>
		implements RegraConditionalValorDao {

	private static final Logger LOGGER = LogManager.getLogger(RegraConditionalValorDaoImpl.class);

	public RegraConditionalValorDaoImpl() throws DaoException {
		super();
	}

	public List<RegraConditionalValorEntity> listByRegraConditionalOperationId(Long regraConditionalOperationId)
			throws DaoException {
		List<RegraConditionalValorEntity> regraConditionalValorEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");
			query = createQuery("listByRegraConditionalOperationId");
			query.setParameter("regraConditionalOperationId", regraConditionalOperationId);

			regraConditionalValorEntities = query.getResultList();

			LOGGER.info("END");
			return regraConditionalValorEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
