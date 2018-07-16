package br.com.spread.qualicorp.wso2.coparticipacao.io.impl;

import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.io.SpreadsheetProcessorService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class SpreadsheetProcessorServiceImpl
		implements SpreadsheetProcessorService {

	public void readInputStream(
			CoParticipacaoContext coParticipacaoContext,
			ProcessorListener processorListener) throws ServiceException {

	}

}
