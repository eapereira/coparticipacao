package br.com.spread.qualicorp.wso2.coparticipacao.report.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.impl.AbstractDaoImpl;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.AutomindCoparticipacaoViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dao.AutomindCoparticipacaoDao;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class AutomindCoparticipacaoDaoImpl extends AbstractDaoImpl<AutomindCoparticipacaoViewEntity>
		implements AutomindCoparticipacaoDao {

	private static final Logger LOGGER = LogManager.getLogger(AutomindCoparticipacaoDaoImpl.class);

	public AutomindCoparticipacaoDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<AutomindCoparticipacaoViewEntity> listByMesAndAno(Integer mes, Integer ano) throws DaoException {
		List<AutomindCoparticipacaoViewEntity> automindCoparticipacaoViewEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByMesAndAno");
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			automindCoparticipacaoViewEntities = query.getResultList();

			LOGGER.info("END");
			return automindCoparticipacaoViewEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

}
