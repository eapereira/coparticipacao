package br.com.spread.qualicorp.wso2.coparticipacao.dao.view.bradesco;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.AutomindResumoViewEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface AutomindResumoDao extends AbstractDao<AutomindResumoViewEntity> {

	List<AutomindResumoViewEntity> listByMesAndAno(Integer mes, Integer ano) throws DaoException;

}
