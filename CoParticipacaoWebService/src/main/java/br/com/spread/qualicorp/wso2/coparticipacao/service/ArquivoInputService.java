package br.com.spread.qualicorp.wso2.coparticipacao.service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface ArquivoInputService extends AbstractService<ArquivoInputUi> {
	/**
	 * Localiza o arquivo de entrada pelo seu nome atráves de uma expressão
	 * <code>regexp</code> definida no banco.
	 * 
	 * @param fileName
	 *            Nome do arquivo que deve ser processado.
	 * @return Verdadeiro se o arquivo estiver cadastrado no banco de dados com
	 *         suas colunas definidas.
	 * @throws ServiceException
	 *             Se ocorrer algum erro, notificaremos o usuário.
	 */
	CoParticipacaoContext findByName(String fileName) throws ServiceException;

	ArquivoInputUi findByContrato(Long contratoId) throws ServiceException;

	ArquivoInputUi findByContratoName(String contratoName) throws ServiceException;
}
