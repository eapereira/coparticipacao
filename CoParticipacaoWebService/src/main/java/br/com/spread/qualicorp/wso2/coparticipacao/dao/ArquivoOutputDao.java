package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface ArquivoOutputDao extends AbstractDao<ArquivoOutputEntity> {

	ArquivoOutputEntity findByArquivoInputId(Long id) throws DaoException;

}
