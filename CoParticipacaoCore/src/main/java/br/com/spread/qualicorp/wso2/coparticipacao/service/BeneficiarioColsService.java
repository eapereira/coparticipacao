package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioColsUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface BeneficiarioColsService extends AbstractService<BeneficiarioColsUi> {

	List<BeneficiarioColsUi> listByArquivoInputId(ArquivoInputUi arquivoInputUi) throws ServiceException;

	List<BeneficiarioColsUi> listByArquivoInputSheetId(ArquivoInputSheetUi arquivoInputSheetUi) throws ServiceException;

}
