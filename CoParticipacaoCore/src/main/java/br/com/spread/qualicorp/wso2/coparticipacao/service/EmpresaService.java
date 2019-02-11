package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.OperadoraUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface EmpresaService extends AbstractService<EmpresaUi> {

	boolean canAutomaticallyCreateBeneficiario(CoParticipacaoContext coParticipacaoContext) throws ServiceException;

	EmpresaUi findByName(String nameEmpresa) throws ServiceException;

	EmpresaUi findByCdEmpresa(String string) throws ServiceException;

	List<EmpresaUi> listByOperadoraId(OperadoraUi operadoraUi) throws ServiceException;

}
