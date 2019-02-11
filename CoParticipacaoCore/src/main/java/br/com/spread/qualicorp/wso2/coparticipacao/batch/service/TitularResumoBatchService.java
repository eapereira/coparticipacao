package br.com.spread.qualicorp.wso2.coparticipacao.batch.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularResumoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface TitularResumoBatchService extends AbstractBatchService<TitularResumoUi> {

	List<TitularResumoUi> listByEmpresa(EmpresaUi empresaUi) throws ServiceException;
}
