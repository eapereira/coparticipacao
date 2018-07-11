package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface DesconhecidoService extends AbstractService<DesconhecidoUi> {
	void createDesconhecido(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException;

	void deleteByMesAndAno(ArquivoInputUi arquivoInputUi, int mes, int ano)
			throws ServiceException;

	List<DesconhecidoUi> listByMesAndAno(
			ArquivoInputUi arquivoInputUi,
			int mes,
			int ano) throws ServiceException;

	void writeDesconhecidosFile(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException;

}
