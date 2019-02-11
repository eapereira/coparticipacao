package br.com.spread.qualicorp.wso2.coparticipacao.report.dao.bradesco;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.AutomindCoparticipacaoViewEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface AutomindCoparticipacaoDao extends AbstractDao<AutomindCoparticipacaoViewEntity> {

	List<AutomindCoparticipacaoViewEntity> listByMesAndAno(Integer mes, Integer ano) throws DaoException;
}
