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

import br.com.spread.qualicorp.wso2.coparticipacao.test.bean.MuitoFacilBean;
import br.com.spread.qualicorp.wso2.coparticipacao.test.config.CoParticipacaoWebServiceConfigurationTest;
import br.com.spread.qualicorp.wso2.coparticipacao.test.service.CoParticipacaoTest;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { CoParticipacaoWebServiceConfigurationTest.class })
// @Transactional
@ActiveProfiles("test")
public class MuitoFacilTest extends CoParticipacaoTest {
	private static final Logger LOGGER = LogManager.getLogger(MuitoFacilTest.class);

	@Autowired
	private MuitoFacilBean muitoFacilBean;

	@Test
	public void testCoparticipacao201806() throws Exception {
		LOGGER.info("BEGIN");

		muitoFacilBean.testCoparticipacao201806(this);

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201807() throws Exception {
		LOGGER.info("BEGIN");

		muitoFacilBean.testCoparticipacao201807(this);

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201807WithUserReturn() throws Exception {
		LOGGER.info("BEGIN");

		muitoFacilBean.testCoparticipacao201807WithUserReturn(this);

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201809() throws Exception {
		LOGGER.info("BEGIN");

		muitoFacilBean.testCoparticipacao201809(this);

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201809AfterUserValidation() throws Exception {
		LOGGER.info("BEGIN");

		muitoFacilBean.testCoparticipacao201809AfterUserValidation(this);

		LOGGER.info("END");
	}

}
