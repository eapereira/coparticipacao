package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputSheetEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface ArquivoOutputSheetDao extends AbstractDao<ArquivoOutputSheetEntity> {

	List<ArquivoOutputSheetEntity> listByArquivoOutputId(Long id) throws DaoException;

}
