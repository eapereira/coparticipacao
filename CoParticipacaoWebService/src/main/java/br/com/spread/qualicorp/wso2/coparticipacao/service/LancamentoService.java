package br.com.spread.qualicorp.wso2.coparticipacao.service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface LancamentoService extends AbstractService<LancamentoUi> {

	void deleteByMesAndAno(int mes, int ano) throws ServiceException;
}
