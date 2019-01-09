package br.com.spread.qualicorp.wso2.coparticipacao.test.service.sulamerica;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.spread.qualicorp.wso2.coparticipacao.test.bean.HocBean;
import br.com.spread.qualicorp.wso2.coparticipacao.test.service.CoParticipacaoTest;

public class OswaldoCruzTest extends CoParticipacaoTest {

	private static final Logger LOGGER = LogManager.getLogger(OswaldoCruzTest.class);

	@Autowired
	private HocBean hocBean;

	@Test
	public void testCoparticipacao201803() throws Exception {
		LOGGER.info("BEGIN");

		hocBean.testCoparticipacao201803(this);

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201803AfterUserValidation() throws Exception {
		LOGGER.info("BEGIN");

		hocBean.testCoparticipacao201803AfterUserValidation(this);

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201807() throws Exception {
		LOGGER.info("BEGIN");

		hocBean.testCoparticipacao201807(this);

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201807AfterUserValidation() throws Exception {
		LOGGER.info("BEGIN");

		hocBean.testCoparticipacao201807AfterUserValidation(this);

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201810() throws Exception {
		LOGGER.info("BEGIN");

		hocBean.testCoparticipacao201810(this);

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201810AfterUserValidation() throws Exception {
		LOGGER.info("BEGIN");

		hocBean.testCoparticipacao201810AfterUserValidation(this);

		LOGGER.info("END");
	}
	
}
