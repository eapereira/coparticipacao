package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputDesconhecidoSheetEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface ArquivoOutputDesconhecidoSheetDao extends AbstractDao<ArquivoOutputDesconhecidoSheetEntity> {

	List<ArquivoOutputDesconhecidoSheetEntity> listByArquivoInputId(Long arquivoInputId) throws DaoException;
}
