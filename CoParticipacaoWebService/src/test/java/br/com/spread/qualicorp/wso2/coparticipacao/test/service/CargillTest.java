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

import br.com.spread.qualicorp.wso2.coparticipacao.test.bean.CargillBean;
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
public class CargillTest extends CoParticipacaoTest {

	private static final Logger LOGGER = LogManager.getLogger(CargillTest.class);

	@Autowired
	private CargillBean cargillBean;

	@Test
	public void testCoparticipacao201807() throws Exception {
		LOGGER.info("BEGIN");

		cargillBean.testCoparticipacao201807();

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201807AfterUserReturn() throws Exception {
		LOGGER.info("BEGIN");

		cargillBean.testCoparticipacao201807AfterUserReturn();

		LOGGER.info("END");
	}
}
