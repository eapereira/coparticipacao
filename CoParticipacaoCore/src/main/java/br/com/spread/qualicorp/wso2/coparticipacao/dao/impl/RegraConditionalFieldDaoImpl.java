package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraConditionalFieldDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraConditionalFieldEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class RegraConditionalFieldDaoImpl extends AbstractDaoImpl<RegraConditionalFieldEntity>
		implements RegraConditionalFieldDao {

	private static final Logger LOGGER = LogManager.getLogger(RegraConditionalFieldDaoImpl.class);

	public RegraConditionalFieldDaoImpl() throws DaoException {
		super();
	}

	public List<RegraConditionalFieldEntity> listByRegraConditionalOperationId(Long regraConditionalOperationId)
			throws DaoException {
		List<RegraConditionalFieldEntity> regraConditionalFieldEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");
			query = createQuery("listByRegraConditionalOperationId");
			query.setParameter("regraConditionalOperationId", regraConditionalOperationId);

			regraConditionalFieldEntities = query.getResultList();

			LOGGER.info("END");
			return regraConditionalFieldEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
