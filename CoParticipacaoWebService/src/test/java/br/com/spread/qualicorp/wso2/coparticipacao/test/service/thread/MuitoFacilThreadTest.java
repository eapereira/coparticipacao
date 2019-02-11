package br.com.spread.qualicorp.wso2.coparticipacao.test.service.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.test.bean.MuitoFacilBean;
import br.com.spread.qualicorp.wso2.coparticipacao.test.service.CoParticipacaoTest;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class MuitoFacilThreadTest extends ThreadTest {

	private static final Logger LOGGER = LogManager.getLogger(MuitoFacilThreadTest.class);

	private MuitoFacilBean muitoFacilBean;

	public MuitoFacilThreadTest(CoParticipacaoTest coParticipacaoTest, MuitoFacilBean muitoFacilBean) {
		super(coParticipacaoTest, "MuitoFacilThreadTest");

		this.muitoFacilBean = muitoFacilBean;
	}

	@Override
	protected void doTest(CoParticipacaoTest coparticipacaoTest) throws Exception {
		LOGGER.info("BEGIN");

		muitoFacilBean.testCoparticipacao201807(coparticipacaoTest);
		muitoFacilBean.testCoparticipacao201807WithUserReturn(coparticipacaoTest);

		LOGGER.info("END");
	}

}
