package br.com.spread.qualicorp.wso2.coparticipacao.ws;

import br.com.spread.qualicorp.webservice.coparticipacao.CoParticipacaoInfo;
import br.com.spread.qualicorp.webservice.coparticipacao.CoParticipacaoRequest;
import br.com.spread.qualicorp.webservice.coparticipacao.CoParticipacaoResponse;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface CoParticipacaoEndPoint {
	String NAMESPACE = "http://qualicorp.spread.com.br/WebService/CoParticipacao";
	String PORT_NAME = "CoParticipacaoPort";

	/**
	 * Método para processar os arquivos de Coparticipação que são enviados
	 * pelas operadoras para a <code>Qualicorp</code>. Aqui são processados
	 * primeiramente dois tipos de arquivos:</br>
	 * <ul>
	 * <li>{@link UseType#FATUCOPA}</li>
	 * <dd>arquivo com os lançamentos feitos pelos títulares dos planos de saúde
	 * ou pelos seus dependentes;</dd>
	 * <li>{@link UseType#MECSAS}</li>
	 * <dd>arquivo de base de dados contendo todos os funcionários e seus
	 * dependentes que a empresa cliente da operadora possui.</dd>
	 * </ul>
	 * </br>
	 * 
	 * O processo sendo executado com sucesso o valor da propriedade
	 * {@link CoParticipacaoInfo#getErrorCode()} será <code>ZERO</code>, se o
	 * valor for <code>UM</code>, significa que existem erros que devem ser
	 * corrigidos.
	 * 
	 * @param coParticipacaoRequest
	 *            Informações com o nome do arquivo e seu caminho, para que o
	 *            processo faça o devido tratamento.
	 * @return Um objeto {@link CoParticipacaoResponse} com as informações se o
	 *         processo foi executado corretamento ou se existem erros.
	 * @throws CoParticipacaoException
	 *             Se ocorrer algum erro notificaremos o usuário.
	 */
	CoParticipacaoResponse processFile(CoParticipacaoRequest coParticipacaoRequest) throws CoParticipacaoException;
}
