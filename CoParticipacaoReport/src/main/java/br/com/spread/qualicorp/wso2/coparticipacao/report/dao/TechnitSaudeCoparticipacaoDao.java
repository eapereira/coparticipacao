package br.com.spread.qualicorp.wso2.coparticipacao.report.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.TechnitSaudeCoparticipacaoViewEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface TechnitSaudeCoparticipacaoDao extends AbstractDao<TechnitSaudeCoparticipacaoViewEntity> {

	List<TechnitSaudeCoparticipacaoViewEntity> listByMesAndAno(Integer mes, Integer ano) throws DaoException;
}
