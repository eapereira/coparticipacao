package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoInputSheetEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface LancamentoInputSheetDao extends AbstractDao<LancamentoInputSheetEntity> {

	List<LancamentoInputSheetEntity> listByArquivoInputId(Long arquivoInputId) throws DaoException;

	LancamentoInputSheetEntity listByArquivoInputSheetId(Long arquivoInputSheetId) throws DaoException;

}
