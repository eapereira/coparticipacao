package br.com.spread.qualicorp.wso2.coparticipacao.test.service;

import java.io.File;

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

	public static final String TEST_PATH = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/";

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
		String fileName = filePath.substring(pos);
		StringBuilder sb = new StringBuilder();

		sb.append(TEST_PATH);
		sb.append(File.separator);
		sb.append(filePath);

		coParticipacaoService.processFile(fileName, sb.toString());
	}

}
