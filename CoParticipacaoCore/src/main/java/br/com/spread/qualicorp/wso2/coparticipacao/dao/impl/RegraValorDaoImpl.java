package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraValorDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraValorEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class RegraValorDaoImpl extends AbstractDaoImpl<RegraValorEntity> implements RegraValorDao {

	private static final Logger LOGGER = LogManager.getLogger(RegraValorDaoImpl.class);

	public RegraValorDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<RegraValorEntity> listByRegraOperationId(Long regraOperationId) throws DaoException {
		List<RegraValorEntity> regraValorEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByRegraOperationId");
			query.setParameter("regraOperationId", regraOperationId);

			regraValorEntities = query.getResultList();

			LOGGER.info("END");
			return regraValorEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
