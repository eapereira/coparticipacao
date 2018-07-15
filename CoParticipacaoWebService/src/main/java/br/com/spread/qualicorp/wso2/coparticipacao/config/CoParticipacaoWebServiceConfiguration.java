package br.com.spread.qualicorp.wso2.coparticipacao.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
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
@EnableJpaRepositories(
		basePackages = { "br.com.spread.qualicorp.wso2.coparticipacao.dao" })
@EnableTransactionManagement
// @PropertySource("persistence-generic-entity.properties")
public class CoParticipacaoWebServiceConfiguration extends WsConfigurerAdapter {
	private static final Logger LOGGER = LogManager
			.getLogger(CoParticipacaoWebServiceConfiguration.class);

	private static final String WEB_SERVICE_URI = "/ws/*";

	private static final String CO_PARTICIPACAO_DS = "jdbc/CoparticipacaoDS";

	@Bean
	public ServletRegistrationBean messageDispatcherServlet(
			ApplicationContext applicationContext)
			throws CoParticipacaoException {
		MessageDispatcherServlet servlet;
		ServletRegistrationBean servletRegistrationBean;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Initializing ServletRegistrationBean:");
			servlet = new MessageDispatcherServlet();
			servlet.setApplicationContext(applicationContext);
			servlet.setTransformWsdlLocations(true);
			servletRegistrationBean = new ServletRegistrationBean(
					servlet,
					WEB_SERVICE_URI);

			LOGGER.info("END");
			return servletRegistrationBean;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource dataSource) throws CoParticipacaoException {
		LocalContainerEntityManagerFactoryBean em;
		JpaVendorAdapter vendorAdapter;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Creating EntityManagerFactoryBean");
			em = new LocalContainerEntityManagerFactoryBean();
			em.setDataSource(dataSource);
			em.setPackagesToScan(
					new String[] {
							"br.com.spread.qualicorp.wso2.coparticipacao.domain" });

			vendorAdapter = new HibernateJpaVendorAdapter();
			em.setJpaVendorAdapter(vendorAdapter);
			em.setJpaProperties(additionalProperties());

			LOGGER.info("END");
			return em;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

	@Bean
	public PlatformTransactionManager transactionManager(
			EntityManagerFactory emf) throws CoParticipacaoException {
		JpaTransactionManager transactionManager;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Creating TransactionManager");
			transactionManager = new JpaTransactionManager();
			transactionManager.setEntityManagerFactory(emf);

			LOGGER.info("END");
			return transactionManager;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation()
			throws CoParticipacaoException {
		PersistenceExceptionTranslationPostProcessor translationPostProcessor;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Creating TranslationPostProcessor");
			translationPostProcessor = new PersistenceExceptionTranslationPostProcessor();

			LOGGER.info("END");
			return translationPostProcessor;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

	private Properties additionalProperties() throws CoParticipacaoException {
		Properties properties;

		try {
			LOGGER.info("BEGIN");
			properties = new Properties();

			LOGGER.info("Using adicional Hibernate properties:");

			// properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
			properties.setProperty(
					"hibernate.dialect",
					"org.hibernate.dialect.MySQL5Dialect");
			properties.setProperty(
					"spring.jpa.properties.hibernate.jdbc.time_zone",
					"UTC");
			properties.setProperty(
					"spring.datasource.driver-class-name",
					"com.mysql.cj.jdbc.Driver");
			properties.setProperty(
					"spring.jpa.datasource.driver-class-name",
					"com.mysql.cj.jdbc.Driver");

			properties.setProperty("spring.jpa.show-sql", "true");
			properties.setProperty("spring.jpa.hibernate.format_sql", "true");
			// properties.setProperty("spring.jpa.hibernate.ddl-auto",
			// "update");
			properties.setProperty(
					"spring.jpa.properties.hibernate.dialect",
					"org.hibernate.dialect.MySQL5Dialect");
			properties.setProperty(
					"spring.jpa.hibernate.naming-strategy",
					"org.hibernate.cfg.ImprovedNamingStrategy");

			LOGGER.info("END");
			return properties;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

	@Bean
	public AnnotationMBeanExporter annotationMBeanExporter()
			throws CoParticipacaoException {
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
	public DataSource dataSource() throws Exception {
		JndiDataSourceLookup dataSourceLookup;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Creating DataSource");
			dataSourceLookup = new JndiDataSourceLookup();

			LOGGER.info("END");
			return dataSourceLookup.getDataSource(CO_PARTICIPACAO_DS);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

	@Bean
	public SaajSoapMessageFactory messageFactory()
			throws CoParticipacaoException {
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
}
