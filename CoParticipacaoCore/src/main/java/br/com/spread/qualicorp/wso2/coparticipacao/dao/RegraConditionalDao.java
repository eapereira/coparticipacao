package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraConditionalEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface RegraConditionalDao extends AbstractDao<RegraConditionalEntity> {

	List<RegraConditionalEntity> listByArquivoInput(Long id) throws DaoException;

	List<RegraConditionalEntity> listByArquivoInputSheetId(Long arquivoInputSheetId) throws DaoException;

}
