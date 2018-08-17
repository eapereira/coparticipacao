package br.com.spread.qualicorp.wso2.coparticipacao.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ws.config.annotation.EnableWs;

import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;

@Configuration
@ComponentScan(basePackages = { "br.com.spread.qualicorp" })
@EnableWs
@EnableAutoConfiguration
@EnableTransactionManagement
@MapperScan("br.com.spread.qualicorp.wso2.coparticipacao.mapper")
@Profile(value = "desenv")
public class JdbcConfiguration {
	private static final Logger LOGGER = LogManager.getLogger(JdbcConfiguration.class);

	private static final String CO_PARTICIPACAO_DS = "jdbc/CoparticipacaoJdbcDS";

	@Bean
	@Qualifier(value = "jdbcTransactionManager")
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSourceJdbc)
			throws CoParticipacaoException {
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

	@Bean
	@Qualifier(value = "dataSourceJdbc")
	public DataSource dataSourceJdbc() throws CoParticipacaoException {
		JndiDataSourceLookup dataSourceLookup;
		DataSource dataSource;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Creating DataSource");

			Class.forName("com.mysql.cj.jdbc.Driver");

			dataSourceLookup = new JndiDataSourceLookup();
			dataSource = dataSourceLookup.getDataSource(CO_PARTICIPACAO_DS);

			LOGGER.info("END");
			return dataSource;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

	@Bean
	public JdbcTemplate jdbcTemplate(@Qualifier("dataSourceJdbc") DataSource dataSourceJdbc)
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

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws CoParticipacaoException {
		SqlSessionFactoryBean sessionFactory;

		try {
			LOGGER.info("BEGIN");

			sessionFactory = new SqlSessionFactoryBean();
			sessionFactory.setDataSource(dataSource);

			return sessionFactory.getObject();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

}
