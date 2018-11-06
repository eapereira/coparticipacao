package br.com.spread.qualicorp.wso2.coparticipacao.test.service.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.test.bean.CargillBean;
import br.com.spread.qualicorp.wso2.coparticipacao.test.service.CoParticipacaoTest;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class CargillThreadTest extends ThreadTest {

	private static final Logger LOGGER = LogManager.getLogger(CargillThreadTest.class);

	private CargillBean cargillBean;

	public CargillThreadTest(CoParticipacaoTest coParticipacaoTest, CargillBean cargillBean) {
		super(coParticipacaoTest, "CargillThreadTest");

		this.cargillBean = cargillBean;
	}

	@Override
	protected void doTest(CoParticipacaoTest coparticipacaoTest) throws Exception {
		LOGGER.info("BEGIN");

		cargillBean.testCoparticipacao201807(coparticipacaoTest);
		cargillBean.testCoparticipacao201807AfterUserReturn(coparticipacaoTest);

		LOGGER.info("END");

	}

}
