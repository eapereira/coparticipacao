package br.com.spread.qualicorp.coparticipacao.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@SpringBootApplication
public class CoParticipacaoWebApplication {
	private static final Logger LOGGER = LogManager.getLogger(CoParticipacaoWebApplication.class);

	public static void main(String[] args) {
		try {
			SpringApplication.run(CoParticipacaoWebApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run(ApplicationArguments applicationArguments) throws Exception {
		LOGGER.info("Starting CoParticipacaoWeb!");
	}
}
