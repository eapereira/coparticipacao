package br.com.spread.qualicorp.wso2.coparticipacao.test.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.spread.qualicorp.wso2.coparticipacao.service.CoParticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.util.StopWatchAdapter;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class CoParticipacaoTest {

	private static final Logger LOGGER = LogManager.getLogger(CoParticipacaoTest.class);

	@Autowired
	private CoParticipacaoService coParticipacaoService;

	private StopWatchAdapter stopWatch;

	@Before
	public void beforeTestClearCoparticipacao() throws Exception {
		LOGGER.info("BEGIN");
		LOGGER.info("Cleanning coparticipacao data to perform test:");

		stopWatch = new StopWatchAdapter();
		stopWatch.start();

		coParticipacaoService.clearCoparticipacao(null);
		LOGGER.info("END");
	}

	@After
	public void afterTestCleanUp() throws Exception {
		LOGGER.info("BEGIN");
		stopWatch.stop();

		LOGGER.info("Test completed with [{}] min:", stopWatch.getTotalTimeMinutes());
		LOGGER.info("END");
	}

	protected void processFile(String filePath) throws Exception {
		int pos = filePath.lastIndexOf("/") + 1;

		coParticipacaoService.processFile(filePath.substring(pos), filePath);
	}

}
