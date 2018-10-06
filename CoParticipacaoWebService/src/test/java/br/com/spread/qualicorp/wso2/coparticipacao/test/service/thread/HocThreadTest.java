package br.com.spread.qualicorp.wso2.coparticipacao.test.service.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.test.bean.HocBean;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class HocThreadTest extends ThreadTest {

	private static final Logger LOGGER = LogManager.getLogger(HocThreadTest.class);

	private HocBean hocBean;

	public HocThreadTest(HocBean hocBean) {
		super("HocThreadTest");

		this.hocBean = hocBean;
	}

	@Override
	protected void doTest() throws Exception {
		LOGGER.info("BEGIN");

		hocBean.testCoparticipacao201803();
		hocBean.testCoparticipacao201803AfterUserValidation();

		LOGGER.info("END");

	}

}
