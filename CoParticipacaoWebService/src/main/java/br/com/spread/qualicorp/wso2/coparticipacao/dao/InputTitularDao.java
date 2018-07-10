package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputTitularEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface InputTitularDao extends AbstractDao<InputTitularEntity> {

	List<InputTitularEntity> listByArquivoInputColsDefId(Long id)
			throws DaoException;

	List<InputTitularEntity> listByArquivoInputId(Long id) throws DaoException;

}
