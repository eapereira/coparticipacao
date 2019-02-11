package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraOperationDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraOperationEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class RegraOperationDaoImpl extends AbstractDaoImpl<RegraOperationEntity> implements RegraOperationDao {

	private static final Logger LOGGER = LogManager.getLogger(RegraOperationDaoImpl.class);

	public RegraOperationDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<RegraOperationEntity> listByRegraId(Long id) throws DaoException {
		List<RegraOperationEntity> regraOperationEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByRegraId");
			query.setParameter("regraId", id);

			regraOperationEntities = query.getResultList();

			LOGGER.info("END");
			return regraOperationEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
