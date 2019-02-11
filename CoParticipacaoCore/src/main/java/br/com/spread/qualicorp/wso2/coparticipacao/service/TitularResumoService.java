package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularResumoUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface TitularResumoService extends AbstractService<TitularResumoUi> {

	List<TitularResumoUi> listByEmpresa(EmpresaUi empresaUi) throws ServiceException;
}
