package br.com.spread.qualicorp.wso2.coparticipacao.test.service.sulamerica;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.spread.qualicorp.wso2.coparticipacao.test.bean.IntervalorBean;
import br.com.spread.qualicorp.wso2.coparticipacao.test.service.CoParticipacaoTest;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class IntervalorTest extends CoParticipacaoTest {
	private static final Logger LOGGER = LogManager.getLogger(IntervalorTest.class);

	@Autowired
	private IntervalorBean intervalorBean;

	@Test
	public void testCoparticipacao201806() throws Exception {
		LOGGER.info("BEGIN");

		intervalorBean.testCoparticipacao201806(this);

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201806AfterUserValidation() throws Exception {
		LOGGER.info("BEGIN");

		intervalorBean.testCoparticipacao201806AfterUserValidation(this);

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201808() throws Exception {
		LOGGER.info("BEGIN");

		intervalorBean.testCoparticipacao201808(this);

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201808AfterUserValidation() throws Exception {
		LOGGER.info("BEGIN");

		intervalorBean.testCoparticipacao201886AfterUserValidation(this);

		LOGGER.info("END");
	}
	
}
