package br.com.spread.qualicorp.wso2.coparticipacao.report.dao.bradesco;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.SpreadSaudeCoparticipacaoResumidaViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.report.domain.bradesco.SpreadSaude;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface SpreadSaudeCoparticipacaoResumidaDao extends AbstractDao<SpreadSaudeCoparticipacaoResumidaViewEntity> {

	List<SpreadSaudeCoparticipacaoResumidaViewEntity> listByMesAndAno(
			Integer mes,
			Integer ano,
			List<SpreadSaude> ativos) throws DaoException;
}
