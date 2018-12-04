package br.com.spread.qualicorp.wso2.coparticipacao.report.dao.bradesco;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.TechnitOdontoCoparticipacaoViewEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface TechnitOdontoCoparticipacaoDao extends AbstractDao<TechnitOdontoCoparticipacaoViewEntity> {

	List<TechnitOdontoCoparticipacaoViewEntity> listByMesAndAno(Integer mes, Integer ano) throws DaoException;

	List<TechnitOdontoCoparticipacaoViewEntity> listByMesAndAnoAndSubFatura(
			Integer mes,
			Integer ano,
			String[] subFaturas) throws DaoException;

	List<TechnitOdontoCoparticipacaoViewEntity> listBySubFatura(String[] subFaturas) throws DaoException;
}
