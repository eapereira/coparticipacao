package br.com.spread.qualicorp.wso2.coparticipacao.test.service.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.spread.qualicorp.wso2.coparticipacao.test.bean.AbbvieBean;
import br.com.spread.qualicorp.wso2.coparticipacao.test.bean.CargillBean;
import br.com.spread.qualicorp.wso2.coparticipacao.test.bean.HocBean;
import br.com.spread.qualicorp.wso2.coparticipacao.test.bean.MarjanBean;
import br.com.spread.qualicorp.wso2.coparticipacao.test.bean.MuitoFacilBean;
import br.com.spread.qualicorp.wso2.coparticipacao.test.config.CoParticipacaoWebServiceConfigurationTest;
import br.com.spread.qualicorp.wso2.coparticipacao.test.service.CoParticipacaoTest;

/**
 * Teste para verificar se o sistema aguenta a execução de vários processos de
 * carga de clientes diferentes ao mesmo tempo sem dar nenhum erro.
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { CoParticipacaoWebServiceConfigurationTest.class })
// @Transactional
@ActiveProfiles("test")
public class MultiThreadTest extends CoParticipacaoTest {

	private static final Logger LOGGER = LogManager.getLogger(MultiThreadTest.class);

	@Autowired
	private CargillBean cargillBean;

	@Autowired
	private AbbvieBean abbvieBean;

	@Autowired
	private MarjanBean marjanBean;

	@Autowired
	private MuitoFacilBean muitoFacilBean;

	@Autowired
	private HocBean hocBean;

	@Test
	public void testMultiEmpresa() throws Exception {
		ExecutorService executorService;
		AbbvieThreadTest abbvieThreadTest;
		MarjanThreadTest marjanThreadTest;
		MuitoFacilThreadTest muitoFacilThreadTest;
		HocThreadTest hocThreadTest;
		CargillThreadTest cargillThreadTest;
		List<Future<ThreadTest>> futures;
		boolean done = false;

		LOGGER.info("BEGIN");

		executorService = Executors.newFixedThreadPool(5);

		abbvieThreadTest = new AbbvieThreadTest(this, abbvieBean);
		marjanThreadTest = new MarjanThreadTest(this, marjanBean);
		muitoFacilThreadTest = new MuitoFacilThreadTest(this, muitoFacilBean);
		hocThreadTest = new HocThreadTest(this, hocBean);
		cargillThreadTest = new CargillThreadTest(this, cargillBean);

		futures = new ArrayList<Future<ThreadTest>>();

		futures.add((Future<ThreadTest>) executorService.submit(abbvieThreadTest));
		futures.add((Future<ThreadTest>) executorService.submit(marjanThreadTest));
		futures.add((Future<ThreadTest>) executorService.submit(muitoFacilThreadTest));
		futures.add((Future<ThreadTest>) executorService.submit(hocThreadTest));
		futures.add((Future<ThreadTest>) executorService.submit(cargillThreadTest));

		// Esperando todas as threads terminarem:
		while (!executorService.isTerminated()) {
			// LOGGER.info("Waiting for all threads to finnish their jobs:");

			done = true;

			for (Future<ThreadTest> future : futures) {
				if (!future.isDone()) {
					done = false;
					break;
				}
			}

			if (done) {
				executorService.shutdown();
			}
		}

		LOGGER.info("MultiThreadTest Result:");

		if (abbvieThreadTest.isDone()) {
			LOGGER.info("Abbvie: ........................ SUCCESS");
		} else {
			LOGGER.info("Abbvie: ........................ FAILURE");
		}

		if (marjanThreadTest.isDone()) {
			LOGGER.info("Marjan: ........................ SUCCESS");
		} else {
			LOGGER.info("Marjan: ........................ FAILURE");
		}

		if (muitoFacilThreadTest.isDone()) {
			LOGGER.info("Muito Fácil: ................... SUCCESS");
		} else {
			LOGGER.info("Muito Fácil: ................... FAILURE");
		}

		if (hocThreadTest.isDone()) {
			LOGGER.info("HOC: ........................... SUCCESS");
		} else {
			LOGGER.info("HOC: ........................... FAILURE");
		}

		if (cargillThreadTest.isDone()) {
			LOGGER.info("Cargill: ....................... SUCCESS");
		} else {
			LOGGER.info("Cargill: ....................... FAILURE");
		}

		// Agora podemos verificar se tudo correu corretamente:
		Assert.assertTrue(abbvieThreadTest.isDone());
		Assert.assertTrue(marjanThreadTest.isDone());
		Assert.assertTrue(muitoFacilThreadTest.isDone());
		Assert.assertTrue(hocThreadTest.isDone());
		Assert.assertTrue(cargillThreadTest.isDone());

		LOGGER.info("END");
	}

}
