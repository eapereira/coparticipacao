package br.com.spread.qualicorp.wso2.coparticipacao.dao.view.bradesco.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.impl.AbstractDaoImpl;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.view.bradesco.LMTransportesCoparticipacaoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.LMTransportesCoparticipacaoViewEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class LMTransportesCoparticipacaoDaoImpl extends AbstractDaoImpl<LMTransportesCoparticipacaoViewEntity>
		implements LMTransportesCoparticipacaoDao {

	private static final Logger LOGGER = LogManager.getLogger(LMTransportesCoparticipacaoDaoImpl.class);

	public LMTransportesCoparticipacaoDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<LMTransportesCoparticipacaoViewEntity> listByMesAndAno(Integer mes, Integer ano) throws DaoException {
		List<LMTransportesCoparticipacaoViewEntity> automindCoparticipacaoViewEntities;
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
