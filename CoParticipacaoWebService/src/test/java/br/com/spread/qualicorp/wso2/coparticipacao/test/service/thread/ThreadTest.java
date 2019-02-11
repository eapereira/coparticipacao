package br.com.spread.qualicorp.wso2.coparticipacao.test.service.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.test.service.CoParticipacaoTest;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class ThreadTest extends Thread {

	private static final Logger LOGGER = LogManager.getLogger(ThreadTest.class);

	private boolean done;

	private CoParticipacaoTest coParticipacaoTest;

	public ThreadTest(CoParticipacaoTest coParticipacaoTest, String name) {
		super(name);
		this.done = false;
		this.coParticipacaoTest = coParticipacaoTest;
	}

	@Override
	public void run() {
		try {
			LOGGER.info("BEGIN");

			doTest(coParticipacaoTest);

			done = true;

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	protected abstract void doTest(CoParticipacaoTest coparticipacaoTest) throws Exception;

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

}
