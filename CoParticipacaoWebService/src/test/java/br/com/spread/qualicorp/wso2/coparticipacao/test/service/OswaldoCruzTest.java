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

import br.com.spread.qualicorp.wso2.coparticipacao.test.bean.HocBean;
import br.com.spread.qualicorp.wso2.coparticipacao.test.config.CoParticipacaoWebServiceConfigurationTest;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { CoParticipacaoWebServiceConfigurationTest.class })
// @Transactional
@ActiveProfiles("test")
public class OswaldoCruzTest extends CoParticipacaoTest {

	private static final Logger LOGGER = LogManager.getLogger(OswaldoCruzTest.class);

	@Autowired
	private HocBean hocBean;

	@Test
	public void testCoparticipacao201803() throws Exception {
		LOGGER.info("BEGIN");

		hocBean.testCoparticipacao201803();

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201803AfterUserValidation() throws Exception {
		LOGGER.info("BEGIN");

		hocBean.testCoparticipacao201803AfterUserValidation();

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201807() throws Exception {
		LOGGER.info("BEGIN");

		hocBean.testCoparticipacao201807();

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201807AfterUserValidation() throws Exception {
		LOGGER.info("BEGIN");

		hocBean.testCoparticipacao201807AfterUserValidation();

		LOGGER.info("END");
	}

}
