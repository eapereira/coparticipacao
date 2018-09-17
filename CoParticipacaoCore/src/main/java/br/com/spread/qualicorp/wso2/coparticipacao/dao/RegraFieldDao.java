package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraFieldEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface RegraFieldDao extends AbstractDao<RegraFieldEntity> {

	List<RegraFieldEntity> listByRegraOperationId(Long regraOperationId) throws DaoException;
}
