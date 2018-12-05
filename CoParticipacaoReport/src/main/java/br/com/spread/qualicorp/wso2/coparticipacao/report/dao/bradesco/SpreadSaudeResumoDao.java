package br.com.spread.qualicorp.wso2.coparticipacao.report.dao.bradesco;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.SpreadSaudeResumoViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.SpreadSaude;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface SpreadSaudeResumoDao extends AbstractDao<SpreadSaudeResumoViewEntity> {

	List<SpreadSaudeResumoViewEntity> listByMesAndAno(Integer mes, Integer ano) throws DaoException;

	List<SpreadSaudeResumoViewEntity> listByMesAndAnoAndSubFaturas(
			Integer mes,
			Integer ano,
			List<SpreadSaude> spreadSaudes) throws DaoException;
}
