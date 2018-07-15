package br.com.spread.qualicorp.wso2.coparticipacao.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@SpringBootApplication
public class CoParticipacaoWebServiceApplication
		extends SpringBootServletInitializer {
	private static final Logger LOGGER = LogManager
			.getLogger(CoParticipacaoWebServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CoParticipacaoWebServiceApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		LOGGER.info("BEGIN");
		LOGGER.info("Starting application [ CoParticipacaoWebService ]:");
		LOGGER.info("END");
		return application.sources(CoParticipacaoWebServiceApplication.class);
	}

}
