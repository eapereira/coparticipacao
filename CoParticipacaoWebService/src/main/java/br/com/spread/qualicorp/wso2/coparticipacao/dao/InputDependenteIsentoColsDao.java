package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputDependenteIsentoColsEntity;

/**
 * 
 * @author edson.apereira
 *
 */
public interface InputDependenteIsentoColsDao
		extends AbstractDao<InputDependenteIsentoColsEntity> {

	List<InputDependenteIsentoColsEntity> listByInputDependenteIsentoId(Long id)
			throws DaoException;

	List<InputDependenteIsentoColsEntity> listByArquivoInputId(Long id)
			throws DaoException;

}
