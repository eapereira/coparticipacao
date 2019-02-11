package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoInputSheetColsEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface LancamentoInputSheetColsDao extends AbstractDao<LancamentoInputSheetColsEntity> {

	List<LancamentoInputSheetColsEntity> listByArquivoInputSheetId(Long arquivoInputSheetId) throws DaoException;

}
