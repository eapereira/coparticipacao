package br.com.spread.qualicorp.wso2.coparticipacao.config;

import javax.servlet.Filter;

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
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
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
@Profile(value = "desenv")
public class CoParticipacaoWebServiceConfiguration extends WsConfigurerAdapter {
	private static final Logger LOGGER = LogManager.getLogger(CoParticipacaoWebServiceConfiguration.class);

	private static final String WEB_SERVICE_URI = "/ws/*";

	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext)
			throws CoParticipacaoException {
		MessageDispatcherServlet servlet;
		ServletRegistrationBean servletRegistrationBean;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Initializing ServletRegistrationBean:");
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

	@Bean
	public SaajSoapMessageFactory messageFactory() throws CoParticipacaoException {
		SaajSoapMessageFactory messageFactory;

		try {
			LOGGER.info("BEGIN");
			messageFactory = new SaajSoapMessageFactory();
			messageFactory.setSoapVersion(SoapVersion.SOAP_11);

			LOGGER.info("END");
			return messageFactory;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

	@Bean
	public Filter getCharacterEncodingFilter() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();

		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);

		return encodingFilter;
	}

}
