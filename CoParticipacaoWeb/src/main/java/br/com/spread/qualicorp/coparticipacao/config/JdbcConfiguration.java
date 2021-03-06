package br.com.spread.qualicorp.coparticipacao.config;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;

@Configuration
@ComponentScan(basePackages = { "br.com.spread.qualicorp" })
@EnableJpaRepositories(basePackages = { "br.com.spread.qualicorp.wso2.coparticipacao.dao" })
@EnableTransactionManagement
@Profile(value = "desenv")
public class JdbcConfiguration {
	private static final Logger LOGGER = LogManager.getLogger(JdbcConfiguration.class);

	private static final String CO_PARTICIPACAO_DS = "jdbc/CoparticipacaoJdbcDS";

	@Bean(name = "jdbcTransactionManager")
	public DataSourceTransactionManager dataSourceTransactionManager(
			@Qualifier("jdbcDataSource2") DataSource dataSourceJdbc) throws CoParticipacaoException {
		DataSourceTransactionManager transactionManager;

		try {
			LOGGER.info("BEGIN");

			transactionManager = new DataSourceTransactionManager(dataSourceJdbc);

			LOGGER.info("END");
			return transactionManager;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

	@Bean(name = "jdbcDataSource2")
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
			hikariConfig.setPoolName("JDBC-DataSource.Web");
			
			hikariDataSource = new HikariDataSource(hikariConfig);

			LOGGER.info("END");
			return hikariDataSource;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

	@Bean
	public JdbcTemplate jdbcTemplate(@Qualifier("jdbcDataSource2") DataSource dataSource)
			throws CoParticipacaoException {
		JdbcTemplate jdbcTemplate;

		try {
			LOGGER.info("BEGIN");

			jdbcTemplate = new JdbcTemplate(dataSource);

			LOGGER.info("END");
			return jdbcTemplate;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}

	}

}
