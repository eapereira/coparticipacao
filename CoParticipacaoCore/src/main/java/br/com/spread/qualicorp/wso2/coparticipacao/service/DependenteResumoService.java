package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteResumoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface DependenteResumoService extends AbstractService<DependenteResumoUi> {

	List<DependenteResumoUi> listByEempresa(EmpresaUi empresaUi) throws ServiceException;
}
