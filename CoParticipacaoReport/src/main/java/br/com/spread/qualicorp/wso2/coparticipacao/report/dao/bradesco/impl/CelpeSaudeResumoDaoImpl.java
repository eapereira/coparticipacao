package br.com.spread.qualicorp.wso2.coparticipacao.report.dao.bradesco.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.impl.AbstractDaoImpl;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.CelpeSaudeResumoViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dao.bradesco.CelpeSaudeResumoDao;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class CelpeSaudeResumoDaoImpl extends AbstractDaoImpl<CelpeSaudeResumoViewEntity>
		implements CelpeSaudeResumoDao {

	private static final Logger LOGGER = LogManager.getLogger(CelpeSaudeResumoDaoImpl.class);

	public CelpeSaudeResumoDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<CelpeSaudeResumoViewEntity> listByMesAndAno(Integer mes, Integer ano) throws DaoException {
		List<CelpeSaudeResumoViewEntity> celpeSaudeResumoViewEntities;
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
