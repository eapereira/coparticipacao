package br.com.spread.qualicorp.wso2.coparticipacao.report.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.LMTransportesResumoViewEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface LMTransportesResumoDao extends AbstractDao<LMTransportesResumoViewEntity> {

	List<LMTransportesResumoViewEntity> listByMesAndAno(Integer mes, Integer ano) throws DaoException;

}
