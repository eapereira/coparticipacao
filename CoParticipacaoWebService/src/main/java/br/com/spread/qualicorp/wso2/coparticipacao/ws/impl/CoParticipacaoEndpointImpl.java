package br.com.spread.qualicorp.wso2.coparticipacao.ws.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.com.spread.qualicorp.webservice.coparticipacao.CoParticipacaoInfo;
import br.com.spread.qualicorp.webservice.coparticipacao.CoParticipacaoRequest;
import br.com.spread.qualicorp.webservice.coparticipacao.CoParticipacaoResponse;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.CoParticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.ws.CoParticipacaoEndPoint;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Endpoint
public class CoParticipacaoEndpointImpl
		implements
		CoParticipacaoEndPoint {

	private static final Logger LOGGER = LogManager.getLogger(CoParticipacaoEndpointImpl.class);

	@Autowired
	private CoParticipacaoService coParticipacaoService;

	@PayloadRoot(namespace = CoParticipacaoEndPoint.NAMESPACE, localPart = "CoParticipacaoRequest")
	@ResponsePayload
	public CoParticipacaoResponse processFile(@RequestPayload CoParticipacaoRequest coParticipacaoRequest)
			throws CoParticipacaoException {
		CoParticipacaoResponse coParticipacaoResponse;
		CoParticipacaoInfo coParticipacaoInfo;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Receiving file [{}] to process:", coParticipacaoRequest.getFileName());
			coParticipacaoResponse = new CoParticipacaoResponse();

			coParticipacaoInfo = coParticipacaoService
					.processFile(coParticipacaoRequest.getFileName(), coParticipacaoRequest.getFilePath());
			coParticipacaoResponse.setCoParticipacaoInfo(coParticipacaoInfo);

			LOGGER.info("END");
			return coParticipacaoResponse;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e.getMessage(), e);
		}
	}

}
