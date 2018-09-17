package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface ArquivoOutputDao extends AbstractDao<ArquivoOutputEntity> {

	ArquivoOutputEntity findByArquivoInputId(Long id, ArquivoType arquivoType) throws DaoException;

	List<ArquivoOutputEntity> listByEmpresaIdAndUseType(Long empresaId, UseType useType) throws DaoException;

}
