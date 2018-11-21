package br.com.spread.qualicorp.wso2.coparticipacao.dao.view.bradesco.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.impl.AbstractDaoImpl;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.view.bradesco.TechnitSaudeCoparticipacaoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.TechnitSaudeCoparticipacaoViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.xml.QueryUtils;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class TechnitSaudeCoparticipacaoDaoImpl extends AbstractDaoImpl<TechnitSaudeCoparticipacaoViewEntity>
		implements TechnitSaudeCoparticipacaoDao {

	private static final Logger LOGGER = LogManager.getLogger(TechnitSaudeCoparticipacaoDaoImpl.class);

	public TechnitSaudeCoparticipacaoDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<TechnitSaudeCoparticipacaoViewEntity> listByMesAndAno(Integer mes, Integer ano) throws DaoException {
		List<TechnitSaudeCoparticipacaoViewEntity> automindCoparticipacaoViewEntities;
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