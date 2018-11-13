package br.com.spread.qualicorp.wso2.coparticipacao.dao.view.bradesco.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.impl.AbstractDaoImpl;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.view.bradesco.LMTransportesResumoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.LMTransportesResumoViewEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class LMTransportesResumoDaoImpl extends AbstractDaoImpl<LMTransportesResumoViewEntity> implements LMTransportesResumoDao {
	private static final Logger LOGGER = LogManager.getLogger(LMTransportesResumoDaoImpl.class);

	public LMTransportesResumoDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<LMTransportesResumoViewEntity> listByMesAndAno(Integer mes, Integer ano) throws DaoException {
		List<LMTransportesResumoViewEntity> automindResumoViewEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByMesAndAno");
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			automindResumoViewEntities = query.getResultList();

			LOGGER.info("END");
			return automindResumoViewEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

}
