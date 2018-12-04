package br.com.spread.qualicorp.wso2.coparticipacao.report.dao.bradesco.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.impl.AbstractDaoImpl;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.SpreadSaudeResumoViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dao.bradesco.SpreadSaudeResumoDao;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class SpreadSaudeResumoDaoImpl extends AbstractDaoImpl<SpreadSaudeResumoViewEntity>
		implements SpreadSaudeResumoDao {

	private static final Logger LOGGER = LogManager.getLogger(SpreadSaudeResumoDaoImpl.class);

	public SpreadSaudeResumoDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<SpreadSaudeResumoViewEntity> listByMesAndAno(Integer mes, Integer ano) throws DaoException {
		List<SpreadSaudeResumoViewEntity> spreadSaudeResumoViewEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByMesAndAno");
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			spreadSaudeResumoViewEntities = query.getResultList();

			LOGGER.info("END");
			return spreadSaudeResumoViewEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

}
