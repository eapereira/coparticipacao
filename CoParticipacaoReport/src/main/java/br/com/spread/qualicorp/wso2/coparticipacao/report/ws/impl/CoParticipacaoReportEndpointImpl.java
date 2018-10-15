package br.com.spread.qualicorp.wso2.coparticipacao.report.ws.impl;

import java.math.BigInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.com.spread.qualicorp.webservice.coparticipacaoreport.CoParticipacaoReportInfo;
import br.com.spread.qualicorp.webservice.coparticipacaoreport.CoParticipacaoReportRequest;
import br.com.spread.qualicorp.webservice.coparticipacaoreport.CoParticipacaoReportResponse;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.ReportService;
import br.com.spread.qualicorp.wso2.coparticipacao.report.ws.CoParticipacaoReportEndpoint;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Endpoint
public class CoParticipacaoReportEndpointImpl implements CoParticipacaoReportEndpoint {

	private static final Logger LOGGER = LogManager.getLogger(CoParticipacaoReportEndpointImpl.class);

	@Autowired
	private ReportService reportService;

	@PayloadRoot(
			namespace = CoParticipacaoReportEndpoint.NAMESPACE,
			localPart = CoParticipacaoReportEndpoint.LOCAL_PART)
	@ResponsePayload
	public CoParticipacaoReportResponse createReport(
			@RequestPayload CoParticipacaoReportRequest coParticipacaoReportRequest) throws CoParticipacaoException {
		CoParticipacaoReportResponse coParticipacaoReportResponse = null;
		CoParticipacaoReportInfo coParticipacaoReportInfo = null;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info(
					"Receiving request to create report for Empresa.ID [{}] with Mes[{}] and Ano[{}]:",
					coParticipacaoReportRequest.getEmpresaId(),
					coParticipacaoReportRequest.getMes(),
					coParticipacaoReportRequest.getAno());

			coParticipacaoReportResponse = new CoParticipacaoReportResponse();

			coParticipacaoReportInfo = reportService.createReport(
					coParticipacaoReportRequest.getEmpresaId(),
					coParticipacaoReportRequest.getMes().intValue(),
					coParticipacaoReportRequest.getAno().intValue());
			
			coParticipacaoReportResponse.setCoParticipacaoReportInfo(coParticipacaoReportInfo);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			coParticipacaoReportInfo.setErrorMessage(e.getMessage());
			coParticipacaoReportInfo.setErrorCode(BigInteger.valueOf(1));

			throw new CoParticipacaoException(e.getMessage(), e);
		} finally {
			LOGGER.info("END");
			return coParticipacaoReportResponse;
		}
	}

}
