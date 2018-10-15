package br.com.spread.qualicorp.wso2.coparticipacao.report.ws;

import br.com.spread.qualicorp.webservice.coparticipacaoreport.CoParticipacaoReportRequest;
import br.com.spread.qualicorp.webservice.coparticipacaoreport.CoParticipacaoReportResponse;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface CoParticipacaoReportEndpoint {
	String NAMESPACE = "http://qualicorp.spread.com.br/WebService/CoParticipacaoReport";
	String PORT_NAME = "CoParticipacaoReportPort";
	String LOCAL_PART = "CoParticipacaoReportRequest";

	CoParticipacaoReportResponse createReport(CoParticipacaoReportRequest coParticipacaoReportRequest)
			throws CoParticipacaoException;
}
