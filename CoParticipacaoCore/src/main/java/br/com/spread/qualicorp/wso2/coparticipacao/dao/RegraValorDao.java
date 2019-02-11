package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraValorEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface RegraValorDao extends AbstractDao<RegraValorEntity> {
	List<RegraValorEntity> listByRegraOperationId(Long regraOperationId) throws DaoException;
}
