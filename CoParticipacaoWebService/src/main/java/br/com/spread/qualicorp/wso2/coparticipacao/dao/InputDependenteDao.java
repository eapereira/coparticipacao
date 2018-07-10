package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputDependenteEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface InputDependenteDao
		extends AbstractDao<InputDependenteEntity> {

	List<InputDependenteEntity> listByArquivoInputColsDefId(Long id)
			throws DaoException;

	List<InputDependenteEntity> listByArquivoInputId(Long id)
			throws DaoException;

}
