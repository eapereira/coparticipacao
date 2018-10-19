package br.com.spread.qualicorp.wso2.coparticipacao.test.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.spread.qualicorp.wso2.coparticipacao.test.bean.MarjanBean;
import br.com.spread.qualicorp.wso2.coparticipacao.test.config.CoParticipacaoWebServiceConfigurationTest;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { CoParticipacaoWebServiceConfigurationTest.class })
// @Transactional
@ActiveProfiles("test")
public class MarjanTest extends CoParticipacaoTest {

	private static final Logger LOGGER = LogManager.getLogger(MarjanTest.class);

	@Autowired
	private MarjanBean marjanBean;

	@Test
	public void testCoparticipacao201806() throws Exception {
		LOGGER.info("BEGIN");

		marjanBean.testCoparticipacao201806();

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201806WithUserReturn() throws Exception {
		LOGGER.info("BEGIN");

		marjanBean.testCoparticipacao201806WithUserReturn();

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201802() throws Exception {
		LOGGER.info("BEGIN");

		marjanBean.testCoparticipacao201802();

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201807() throws Exception {
		LOGGER.info("BEGIN");

		marjanBean.testCoparticipacao201807();

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201808() throws Exception {
		LOGGER.info("BEGIN");

		marjanBean.testCoparticipacao201808();

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201808AfterUserReturn() throws Exception {
		LOGGER.info("BEGIN");

		marjanBean.testCoparticipacao201808AfterUserReturn();

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201810() throws Exception {
		LOGGER.info("BEGIN");

		marjanBean.testCoparticipacao201810();

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201810AfterUserReturn() throws Exception {
		LOGGER.info("BEGIN");

		marjanBean.testCoparticipacao201810AfterUserReturn();

		LOGGER.info("END");
	}
	
}
