package br.com.spread.qualicorp.wso2.coparticipacao.test.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Configuration
@ComponentScan(basePackages = { "br.com.spread.qualicorp" })
@EnableWs
@EnableAutoConfiguration
@Profile(value = "test")
public class CoParticipacaoWebServiceConfigurationTest extends WsConfigurerAdapter {
	private static final Logger LOGGER = LogManager.getLogger(CoParticipacaoWebServiceConfigurationTest.class);

	private static final String WEB_SERVICE_URI = "/ws/*";

	private static final String CO_PARTICIPACAO_DS = "jdbc/CoparticipacaoDS";

	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext)
			throws CoParticipacaoException {
		MessageDispatcherServlet servlet;
		ServletRegistrationBean servletRegistrationBean;

		try {
			LOGGER.info("BEGIN");
			servlet = new MessageDispatcherServlet();
			servlet.setApplicationContext(applicationContext);
			servlet.setTransformWsdlLocations(true);
			servletRegistrationBean = new ServletRegistrationBean(servlet, WEB_SERVICE_URI);

			LOGGER.info("END");
			return servletRegistrationBean;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

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
