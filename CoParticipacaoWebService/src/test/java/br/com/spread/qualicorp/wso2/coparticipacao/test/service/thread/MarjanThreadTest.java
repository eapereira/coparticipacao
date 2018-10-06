package br.com.spread.qualicorp.wso2.coparticipacao.test.service.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.test.bean.MarjanBean;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class MarjanThreadTest extends ThreadTest {

	private static final Logger LOGGER = LogManager.getLogger(MarjanThreadTest.class);

	private MarjanBean marjanBean;

	public MarjanThreadTest(MarjanBean marjanBean) {
		super("MarjanThreadTest");

		this.marjanBean = marjanBean;
	}

	@Override
	protected void doTest() throws Exception {
		LOGGER.info("BEGIN");

		marjanBean.testCoparticipacao201806();
		marjanBean.testCoparticipacao201806WithUserReturn();

		LOGGER.info("END");
	}

}
