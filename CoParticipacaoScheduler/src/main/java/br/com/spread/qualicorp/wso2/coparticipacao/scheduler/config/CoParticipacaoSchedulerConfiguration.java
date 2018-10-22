package br.com.spread.qualicorp.wso2.coparticipacao.scheduler.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.scheduling.annotation.EnableScheduling;

import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Configuration
@ComponentScan(basePackages = { "br.com.spread.qualicorp.wso2.coparticipacao.scheduler" })
@EnableScheduling
@EnableAutoConfiguration
@Profile(value = "desenv")
public class CoParticipacaoSchedulerConfiguration  {
	private static final Logger LOGGER = LogManager.getLogger(CoParticipacaoSchedulerConfiguration.class);

	@Bean
	public AnnotationMBeanExporter annotationMBeanExporter() throws CoParticipacaoException {
		try {
			LOGGER.info("BEGIN");
			AnnotationMBeanExporter annotationMBeanExporter = new AnnotationMBeanExporter();
			annotationMBeanExporter.addExcludedBean("dataSource");

			LOGGER.info("END");
			return annotationMBeanExporter;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

	
}
