package br.com.spread.qualicorp.wso2.coparticipacao.test.service.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.test.bean.AbbvieBean;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class AbbvieThreadTest extends ThreadTest {

	private static final Logger LOGGER = LogManager.getLogger(AbbvieThreadTest.class);

	private AbbvieBean abbvieBean;

	public AbbvieThreadTest(AbbvieBean abbvieBean) {
		super("AbbvieThread");

		this.abbvieBean = abbvieBean;
	}

	@Override
	protected void doTest() throws Exception {
		LOGGER.info("BEGIN");

		abbvieBean.testCoparticipacao201808();
		abbvieBean.testCoparticipacao201808WithUserReturn();

		LOGGER.info("END");
	}
}
