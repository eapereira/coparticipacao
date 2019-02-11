package br.com.spread.qualicorp.wso2.coparticipacao.test.service.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.test.bean.MarjanBean;
import br.com.spread.qualicorp.wso2.coparticipacao.test.service.CoParticipacaoTest;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class MarjanThreadTest extends ThreadTest {

	private static final Logger LOGGER = LogManager.getLogger(MarjanThreadTest.class);

	private MarjanBean marjanBean;

	public MarjanThreadTest(CoParticipacaoTest coParticipacaoTest, MarjanBean marjanBean) {
		super(coParticipacaoTest, "MarjanThreadTest");

		this.marjanBean = marjanBean;
	}

	@Override
	protected void doTest(CoParticipacaoTest coparticipacaoTest) throws Exception {
		LOGGER.info("BEGIN");

		marjanBean.testCoparticipacao201806(coparticipacaoTest);
		marjanBean.testCoparticipacao201806WithUserReturn(coparticipacaoTest);

		LOGGER.info("END");
	}

}
