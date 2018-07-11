package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputOutputDesconhecidoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface ArquivoInputOutputDesconhecidoDao
		extends AbstractDao<ArquivoInputOutputDesconhecidoEntity> {

	List<ArquivoInputOutputDesconhecidoEntity> listByArquivoInputId(Long id)
			throws DaoException;
}
