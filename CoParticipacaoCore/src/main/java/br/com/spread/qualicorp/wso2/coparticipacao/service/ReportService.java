package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ReportUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface ReportService extends AbstractService<ReportUi> {

	List<ReportUi> listByEmpresa(EmpresaUi empresaUi) throws ServiceException;

	boolean hasReports(EmpresaUi empresaUi) throws ServiceException;;

	void writeReport(CoParticipacaoContext coParticipacaoContext) throws ServiceException;;
}
