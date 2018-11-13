package br.com.spread.qualicorp.coparticipacao.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Configuration
@ComponentScan(basePackages = { "br.com.spread.qualicorp" })
@EnableJpaRepositories(basePackages = { "br.com.spread.qualicorp.wso2.coparticipacao.dao" })
@EnableTransactionManagement
// @PropertySource("classpath:jpa.properties")
@Profile(value = "desenv")
public class JpaConfiguration {
	private static final Logger LOGGER = LogManager.getLogger(JpaConfiguration.class);

	private static final String CO_PARTICIPACAO_DS = "jdbc/CoparticipacaoDS";

	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource)
			throws CoParticipacaoException {
		LocalContainerEntityManagerFactoryBean em;
		JpaVendorAdapter vendorAdapter;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Creating EntityManagerFactoryBean");
			em = new LocalContainerEntityManagerFactoryBean();
			em.setDataSource(dataSource);
			em.setPackagesToScan(new String[] { "br.com.spread.qualicorp.wso2.coparticipacao.domain" });

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
	@Bean(name = "jpaTransactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) throws CoParticipacaoException {
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
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() throws CoParticipacaoException {
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
			properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
			properties.setProperty("spring.jpa.properties.hibernate.jdbc.time_zone", "UTC");
			properties.setProperty("spring.jpa.datasource.driver-class-name", "com.mysql.cj.jdbc.Driver");
			properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");

			properties.setProperty("spring.datasource.hikari.driverClassName", "com.mysql.cj.jdbc.Driver");
			properties.setProperty(
					"spring.datasource.hikari.dataSourceClassName",
					"com.mysql.jdbc.jdbc2.optional.MysqlDataSource");

			//properties.setProperty("spring.jpa.show-sql", "true");
			//properties.setProperty("spring.jpa.hibernate.format_sql", "true");
			// properties.setProperty("spring.jpa.hibernate.ddl-auto",
			// "update");
			properties.setProperty("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
			properties.setProperty("spring.jpa.hibernate.naming-strategy", "org.hibernate.cfg.ImprovedNamingStrategy");

			LOGGER.info("END");
			return properties;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

	@Primary
	@Bean
	public DataSource dataSource() throws Exception {
		JndiDataSourceLookup dataSourceLookup;
		DataSource dataSource;
		HikariDataSource hikariDataSource;
		HikariConfig hikariConfig;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Creating DataSource");

			dataSourceLookup = new JndiDataSourceLookup();
			dataSourceLookup.setResourceRef(true);
			dataSource = dataSourceLookup.getDataSource(CO_PARTICIPACAO_DS);

			hikariConfig = new HikariConfig();
			hikariConfig.setDataSource(dataSource);
			hikariDataSource = new HikariDataSource(hikariConfig);

			LOGGER.info("END");
			return hikariDataSource;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}
}
