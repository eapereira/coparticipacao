package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface ArquivoOutputSheetService extends AbstractService<ArquivoOutputSheetUi> {

	List<ArquivoOutputSheetUi> listByArquivoOutputId(ArquivoOutputUi arquivoOutputUi) throws ServiceException;
}
