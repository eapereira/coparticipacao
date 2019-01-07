package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegisterUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface RegisterService extends AbstractService<RegisterUi> {

	List<RegisterUi> listByArquivoInputSheet(ArquivoInputSheetUi arquivoInputSheetUi) throws ServiceException;
}
