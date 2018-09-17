package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputTitularIsentoColsEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface InputTitularIsentoColsDao
		extends AbstractDao<InputTitularIsentoColsEntity> {

	List<InputTitularIsentoColsEntity> listByInputTitularIsentoId(Long id)
			throws DaoException;

	List<InputTitularIsentoColsEntity> listByArquivoInputId(Long id)
			throws DaoException;

}
