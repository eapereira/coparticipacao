package br.com.spread.qualicorp.wso2.coparticipacao.test.service.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.test.bean.CargillBean;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class CargillThreadTest extends ThreadTest {

	private static final Logger LOGGER = LogManager.getLogger(CargillThreadTest.class);

	private CargillBean cargillBean;

	public CargillThreadTest(CargillBean cargillBean) {
		super("CargillThreadTest");

		this.cargillBean = cargillBean;
	}

	@Override
	protected void doTest() throws Exception {
		LOGGER.info("BEGIN");

		cargillBean.testCoparticipacao201807();
		cargillBean.testCoparticipacao201807AfterUserReturn();

		LOGGER.info("END");

	}

}
