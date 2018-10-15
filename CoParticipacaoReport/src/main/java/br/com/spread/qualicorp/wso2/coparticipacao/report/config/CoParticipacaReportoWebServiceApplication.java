package br.com.spread.qualicorp.wso2.coparticipacao.report.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

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
public class CoParticipacaReportoWebServiceApplication extends SpringBootServletInitializer {
	private static final Logger LOGGER = LogManager.getLogger(CoParticipacaReportoWebServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CoParticipacaReportoWebServiceApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		LOGGER.info("BEGIN");
		LOGGER.info("Starting application [ CoParticipacaoWebService ]:");
		LOGGER.info("END");
		return application.sources(CoParticipacaReportoWebServiceApplication.class);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.setInitParameter("spring.profiles.active", "desenv");

		super.onStartup(servletContext);
	}

}
