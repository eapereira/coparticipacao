package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputLancamentoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface InputLancamentoDao
		extends AbstractDao<InputLancamentoEntity> {

	List<InputLancamentoEntity> listByArquivoInputColsDefId(Long id)
			throws DaoException;

	List<InputLancamentoEntity> listByArquivoInputId(Long id)
			throws DaoException;

}
