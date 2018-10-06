package br.com.spread.qualicorp.wso2.coparticipacao.batch.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteResumoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface DependenteResumoBatchService extends AbstractBatchService<DependenteResumoUi> {

	List<DependenteResumoUi> listByEmpresa(EmpresaUi empresaUi) throws ServiceException;
}
