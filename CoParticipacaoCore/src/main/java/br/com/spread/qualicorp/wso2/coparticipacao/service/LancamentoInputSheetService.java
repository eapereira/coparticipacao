package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoInputSheetUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface LancamentoInputSheetService extends AbstractService<LancamentoInputSheetUi> {

	List<LancamentoInputSheetUi> listByArquivoInput(ArquivoInputUi arquivoInputUi) throws ServiceException;

	LancamentoInputSheetUi findByArquivoInputSheet(ArquivoInputSheetUi arquivoInputSheetUi) throws ServiceException;

}
