package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface ArquivoInputDao
		extends
		AbstractDao<ArquivoInputEntity> {

	ArquivoInputEntity findByContrato(Long contratoId) throws DaoException;

}
