package br.com.spread.qualicorp.wso2.coparticipacao.dao.view.bradesco.impl;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.impl.AbstractDaoImpl;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.view.bradesco.TechnitOdontoCoparticipacaoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.TechnitOdontoCoparticipacaoViewEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class TechnitOdontoCoparticipacaoDaoImpl extends AbstractDaoImpl<TechnitOdontoCoparticipacaoViewEntity>
		implements TechnitOdontoCoparticipacaoDao {

	private static final Logger LOGGER = LogManager.getLogger(TechnitOdontoCoparticipacaoDaoImpl.class);

	public TechnitOdontoCoparticipacaoDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<TechnitOdontoCoparticipacaoViewEntity> listByMesAndAno(Integer mes, Integer ano) throws DaoException {
		List<TechnitOdontoCoparticipacaoViewEntity> automindCoparticipacaoViewEntities;
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

	public List<TechnitOdontoCoparticipacaoViewEntity> listByMesAndAnoAndSubFatura(
			Integer mes,
			Integer ano,
			String[] subFaturas) throws DaoException {
		List<TechnitOdontoCoparticipacaoViewEntity> automindCoparticipacaoViewEntities;
		Query query;
		List<String> listSubFaturas;

		try {
			LOGGER.info("BEGIN");

			listSubFaturas = Arrays.asList(subFaturas);

			query = createQuery("listByMesAndAnoAndSubFaturas");
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);
			query.setParameter("subFaturas", listSubFaturas);

			automindCoparticipacaoViewEntities = query.getResultList();

			LOGGER.info("END");
			return automindCoparticipacaoViewEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

}
