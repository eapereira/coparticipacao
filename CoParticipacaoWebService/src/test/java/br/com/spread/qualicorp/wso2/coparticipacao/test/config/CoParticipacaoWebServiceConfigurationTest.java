package br.com.spread.qualicorp.wso2.coparticipacao.test.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
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
@EnableJpaRepositories(
		basePackages = "br.com.spread.qualicorp.wso2.coparticipacao.dao")
@EnableTransactionManagement
// @PropertySource("persistence-generic-entity.properties")
public class CoParticipacaoWebServiceConfigurationTest
		extends WsConfigurerAdapter {
	private static final Logger LOGGER = LogManager
			.getLogger(CoParticipacaoWebServiceConfigurationTest.class);

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

	@Primary
	@Bean
	@Qualifier(value = "jpaTransactionManager")
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

	private Properties additionalProperties() {
		Properties properties = new Properties();
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

		return properties;
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

	/**
	 * Em teste não temos o tomcat para criar o {@link DataSource}, então
	 * devemos criar um com conexão direta.
	 * 
	 * @return
	 * @throws CoParticipacaoException
	 */
	@Primary
	@Bean
	public DataSource dataSource() throws CoParticipacaoException {
		try {
			LOGGER.info("BEGIN");
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSource.setUrl(
					"jdbc:mysql://mysql.desenv:3306/coparticipacao?useUnicode=true&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&useLegacyDatetimeCode=false");
			dataSource.setUsername("coparticipacao");
			dataSource.setPassword("coparticipacao");

			LOGGER.info("END");
			return dataSource;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}
}
