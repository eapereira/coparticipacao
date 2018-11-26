package br.com.spread.qualicorp.wso2.coparticipacao.report.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.impl.AbstractDaoImpl;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.CelpeSaudeRateioViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dao.CelpeSaudeRateioDao;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class CelpeSaudeRateioDaoImpl extends AbstractDaoImpl<CelpeSaudeRateioViewEntity>
		implements CelpeSaudeRateioDao {

	private static final Logger LOGGER = LogManager.getLogger(CelpeSaudeRateioDaoImpl.class);

	public CelpeSaudeRateioDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<CelpeSaudeRateioViewEntity> listByMesAndAno(Integer mes, Integer ano) throws DaoException {
		List<CelpeSaudeRateioViewEntity> celpeSaudeRateioViewEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByMesAndAno");
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			celpeSaudeRateioViewEntities = query.getResultList();

			LOGGER.info("END");
			return celpeSaudeRateioViewEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

}
