package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoInputEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface LancamentoInputDao extends AbstractDao<LancamentoInputEntity> {

	List<LancamentoInputEntity> listByArquivoInputId(Long id) throws DaoException;

}
