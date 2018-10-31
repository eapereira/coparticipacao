package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoInputColsEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface LancamentoInputColsDao extends AbstractDao<LancamentoInputColsEntity> {

	List<LancamentoInputColsEntity> listByLancamentoInputId(Long lancamentoInputId) throws DaoException;

	List<LancamentoInputColsEntity> listByArquivoInputId(Long arquivoInputId) throws DaoException;

}
