package br.com.spread.qualicorp.wso2.coparticipacao.report.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.CelpeSaudeRateioViewEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface CelpeSaudeRateioDao extends AbstractDao<CelpeSaudeRateioViewEntity> {

	List<CelpeSaudeRateioViewEntity> listByMesAndAno(Integer mes, Integer ano) throws DaoException;
}
