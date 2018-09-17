package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputColsDefEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface ArquivoInputColsDefDao
		extends
		AbstractDao<ArquivoInputColsDefEntity> {

	List<ArquivoInputColsDefEntity> listByArquivoInput(Long id) throws DaoException;

}
