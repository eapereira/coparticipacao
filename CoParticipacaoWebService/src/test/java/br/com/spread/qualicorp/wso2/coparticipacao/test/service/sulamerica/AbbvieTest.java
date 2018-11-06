package br.com.spread.qualicorp.wso2.coparticipacao.test.service.sulamerica;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.spread.qualicorp.wso2.coparticipacao.test.bean.AbbvieBean;
import br.com.spread.qualicorp.wso2.coparticipacao.test.config.CoParticipacaoWebServiceConfigurationTest;
import br.com.spread.qualicorp.wso2.coparticipacao.test.service.CoParticipacaoTest;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { CoParticipacaoWebServiceConfigurationTest.class })
// @Transactional
@ActiveProfiles("test")
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
