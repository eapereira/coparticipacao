package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface LancamentoJdbcDao extends AbstractJdbcDao<LancamentoEntity> {

	void deleteByMesAndAno(Long contratoId, int mes, int ano) throws DaoException;

}
