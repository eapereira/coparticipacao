package br.com.spread.qualicorp.wso2.coparticipacao.report.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.impl.AbstractDaoImpl;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.CelpeSaudeResumoDetailViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dao.CelpeSaudeResumoDetailDao;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class CelpeSaudeResumoDetailDaoImpl extends AbstractDaoImpl<CelpeSaudeResumoDetailViewEntity>
		implements CelpeSaudeResumoDetailDao {

	private static final Logger LOGGER = LogManager.getLogger(CelpeSaudeResumoDetailDaoImpl.class);

	public CelpeSaudeResumoDetailDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<CelpeSaudeResumoDetailViewEntity> listByMesAndAno(Integer mes, Integer ano) throws DaoException {
		List<CelpeSaudeResumoDetailViewEntity> celpeSaudeResumoViewEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByMesAndAno");
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			celpeSaudeResumoViewEntities = query.getResultList();

			LOGGER.info("END");
			return celpeSaudeResumoViewEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

}
