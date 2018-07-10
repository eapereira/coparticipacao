package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ContratoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface ContratoDao
		extends
		AbstractDao<ContratoEntity> {

	ContratoEntity findByCdContrato(String contratoName) throws DaoException;

}
