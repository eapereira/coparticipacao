package br.com.spread.qualicorp.wso2.coparticipacao.test.service.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.test.bean.HocBean;
import br.com.spread.qualicorp.wso2.coparticipacao.test.service.CoParticipacaoTest;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class HocThreadTest extends ThreadTest {

	private static final Logger LOGGER = LogManager.getLogger(HocThreadTest.class);

	private HocBean hocBean;

	public HocThreadTest(CoParticipacaoTest coParticipacaoTest, HocBean hocBean) {
		super(coParticipacaoTest, "HocThreadTest");

		this.hocBean = hocBean;
	}

	@Override
	protected void doTest(CoParticipacaoTest coparticipacaoTest) throws Exception {
		LOGGER.info("BEGIN");

		hocBean.testCoparticipacao201803(coparticipacaoTest);
		hocBean.testCoparticipacao201803AfterUserValidation(coparticipacaoTest);

		LOGGER.info("END");

	}

}
