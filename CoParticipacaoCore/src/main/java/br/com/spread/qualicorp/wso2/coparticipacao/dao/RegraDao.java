package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface RegraDao extends AbstractDao<RegraEntity> {

	List<RegraEntity> listByArquivoInputId(Long id) throws DaoException;

	List<RegraEntity> listByArquivoInputSheetId(Long arquivoInputSheetId) throws DaoException;

}
