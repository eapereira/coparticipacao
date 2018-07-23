package br.com.spread.qualicorp.wso2.coparticipacao.test.config;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ws.config.annotation.EnableWs;

import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;

@Configuration
@ComponentScan(basePackages = { "br.com.spread.qualicorp" })
@EnableWs
@EnableAutoConfiguration
@EnableTransactionManagement
public class JdbcConfigurationTest {
	private static final Logger LOGGER = LogManager
			.getLogger(JdbcConfigurationTest.class);

	@Bean
	@Qualifier(value = "jdbcTransactionManager")
	public DataSourceTransactionManager dataSourceTransactionManager(
			DataSource dataSourceJdbc) throws CoParticipacaoException {
		DataSourceTransactionManager transactionManager;

		try {
			LOGGER.info("BEGIN");

			transactionManager = new DataSourceTransactionManager(
					dataSourceJdbc);

			LOGGER.info("END");
			return transactionManager;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

	@Bean
	@Qualifier(value = "dataSourceJdbc")
	public DataSource dataSourceJdbc() throws CoParticipacaoException {
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

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSourceJdbc)
			throws CoParticipacaoException {
		JdbcTemplate jdbcTemplate;

		try {
			LOGGER.info("BEGIN");

			jdbcTemplate = new JdbcTemplate(dataSourceJdbc);

			LOGGER.info("END");
			return jdbcTemplate;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}

	}
}
