package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraFieldDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraFieldEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class RegraFieldDaoImpl extends AbstractDaoImpl<RegraFieldEntity> implements RegraFieldDao {

	private static final Logger LOGGER = LogManager.getLogger(RegraFieldDaoImpl.class);

	public RegraFieldDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<RegraFieldEntity> listByRegraOperationId(Long regraOperationId) throws DaoException {
		List<RegraFieldEntity> regraFieldEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByRegraOperationId");
			query.setParameter("regraOperationId", regraOperationId);

			regraFieldEntities = query.getResultList();

			LOGGER.info("END");
			return regraFieldEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
