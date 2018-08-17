package br.com.spread.qualicorp.wso2.coparticipacao.service;

import br.com.spread.qualicorp.webservice.coparticipacao.CoParticipacaoInfo;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface CoParticipacaoService {

	/**
	 * 
	 * @param fileName
	 *            Nome do arquivo que deve ser processado.
	 * @param filePath
	 *            Caminho no <code>FileSystem</code> do servidor aonde esta o
	 *            arquivo.
	 * @return
	 * @throws ServiceException
	 *             Se ocorrer algum erro, notificaremos o usuário.
	 */
	CoParticipacaoInfo processFile(String fileName, String filePath) throws ServiceException;

	void clearCoparticipacao(EmpresaUi empresaUi) throws ServiceException;

}
