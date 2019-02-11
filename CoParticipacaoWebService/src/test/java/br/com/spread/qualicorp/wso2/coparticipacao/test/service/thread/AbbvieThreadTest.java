package br.com.spread.qualicorp.wso2.coparticipacao.test.service.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.test.bean.AbbvieBean;
import br.com.spread.qualicorp.wso2.coparticipacao.test.service.CoParticipacaoTest;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class AbbvieThreadTest extends ThreadTest {

	private static final Logger LOGGER = LogManager.getLogger(AbbvieThreadTest.class);

	private AbbvieBean abbvieBean;

	public AbbvieThreadTest(CoParticipacaoTest coParticipacaoTest,AbbvieBean abbvieBean) {
		super(coParticipacaoTest,"AbbvieThread");

		this.abbvieBean = abbvieBean;
	}

	@Override
	protected void doTest(CoParticipacaoTest coparticipacaoTest) throws Exception {
		LOGGER.info("BEGIN");

		abbvieBean.testCoparticipacao201808(coparticipacaoTest);
		abbvieBean.testCoparticipacao201808WithUserReturn(coparticipacaoTest);

		LOGGER.info("END");
	}
}
