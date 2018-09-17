package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.IsentoInputSheetUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface IsentoInputSheetService extends AbstractService<IsentoInputSheetUi> {

	List<IsentoInputSheetUi> listByArquivoInputId(ArquivoInputUi arquivoInputUi) throws ServiceException;

}
