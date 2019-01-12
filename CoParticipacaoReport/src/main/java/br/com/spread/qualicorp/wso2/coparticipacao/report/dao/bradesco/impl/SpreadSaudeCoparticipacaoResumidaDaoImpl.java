package br.com.spread.qualicorp.wso2.coparticipacao.report.dao.bradesco.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.impl.AbstractDaoImpl;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.SpreadSaudeCoparticipacaoResumidaViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dao.bradesco.SpreadSaudeCoparticipacaoResumidaDao;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.SpreadSaude;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class SpreadSaudeCoparticipacaoResumidaDaoImpl extends
		AbstractDaoImpl<SpreadSaudeCoparticipacaoResumidaViewEntity> implements SpreadSaudeCoparticipacaoResumidaDao {

	private static final Logger LOGGER = LogManager.getLogger(SpreadSaudeCoparticipacaoResumidaDaoImpl.class);

	public SpreadSaudeCoparticipacaoResumidaDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<SpreadSaudeCoparticipacaoResumidaViewEntity> listByMesAndAno(
			Integer mes,
			Integer ano,
			List<SpreadSaude> ativos,
			List<SpreadSaude> inativos) throws DaoException {
		List<SpreadSaudeCoparticipacaoResumidaViewEntity> spreadSaudeCoparticipacaoResumidaViewEntities;
		Query query;
		List<String> subfaturaAtivos;
		List<String> subfaturaInativos;

		try {
			LOGGER.info("BEGIN");

			subfaturaAtivos = new ArrayList<>();

			for (SpreadSaude spreadSaude : ativos) {
				subfaturaAtivos.add(StringUtils.leftPad(spreadSaude.getId().toString(), 3, "0"));
			}

			subfaturaInativos = new ArrayList<>();

			for (SpreadSaude spreadSaude : inativos) {
				subfaturaInativos.add(StringUtils.leftPad(spreadSaude.getId().toString(), 3, "0"));
			}

			query = createQuery("listByMesAndAno");
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);
			query.setParameter("subFaturaAtivos", subfaturaAtivos);
			query.setParameter("subFaturaInativos", subfaturaInativos);

			spreadSaudeCoparticipacaoResumidaViewEntities = query.getResultList();

			LOGGER.info("END");
			return spreadSaudeCoparticipacaoResumidaViewEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

}
