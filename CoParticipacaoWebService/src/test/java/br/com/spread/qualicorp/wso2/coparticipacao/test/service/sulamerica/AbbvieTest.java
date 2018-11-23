package br.com.spread.qualicorp.wso2.coparticipacao.test.service.sulamerica;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.spread.qualicorp.wso2.coparticipacao.test.bean.AbbvieBean;
import br.com.spread.qualicorp.wso2.coparticipacao.test.service.CoParticipacaoTest;

public class AbbvieTest extends CoParticipacaoTest {
	private static final Logger LOGGER = LogManager.getLogger(AbbvieTest.class);

	@Autowired
	private AbbvieBean abbvieBean;

	@Test
	public void testCoparticipacao201808() throws Exception {
		LOGGER.info("BEGIN");

		abbvieBean.testCoparticipacao201808(this);

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201808WithUserReturn() throws Exception {
		LOGGER.info("BEGIN");

		abbvieBean.testCoparticipacao201808WithUserReturn(this);

		LOGGER.info("END");
	}

}
