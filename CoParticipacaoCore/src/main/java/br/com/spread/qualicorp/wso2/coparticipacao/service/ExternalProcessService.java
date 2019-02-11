package br.com.spread.qualicorp.wso2.coparticipacao.service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface ExternalProcessService {

	void createNameArquivoOutput(ArquivoExecucaoUi arquivoExecucaoUi) throws ServiceException;

	boolean isJobDone(ArquivoExecucaoUi arquivoExecucaoUi) throws ServiceException;

}
