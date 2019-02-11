package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;

/**
 * 
 * @author edson.apereira
 *
 */
public interface ArquivoInputSheetService extends AbstractService<ArquivoInputSheetUi> {

	List<ArquivoInputSheetUi> listByArquivoInput(ArquivoInputUi arquivoInputUi) throws ServiceException;

}
