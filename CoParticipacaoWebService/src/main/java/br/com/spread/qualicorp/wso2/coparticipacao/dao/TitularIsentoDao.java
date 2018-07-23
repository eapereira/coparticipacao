package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularIsentoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface TitularIsentoDao extends AbstractDao<TitularIsentoEntity> {

	void deleteByMesAndAno(Long empresaId, Integer mes, Integer ano) throws DaoException;

}
