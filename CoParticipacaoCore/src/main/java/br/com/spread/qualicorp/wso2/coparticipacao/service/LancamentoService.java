package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface LancamentoService extends AbstractService<LancamentoUi> {

	void deleteByMesAndAno(ContratoUi contratoUi, int mes, int ano) throws ServiceException;

	List<LancamentoUi> listByEmpresaId(EmpresaUi empresaUi) throws ServiceException;
}
