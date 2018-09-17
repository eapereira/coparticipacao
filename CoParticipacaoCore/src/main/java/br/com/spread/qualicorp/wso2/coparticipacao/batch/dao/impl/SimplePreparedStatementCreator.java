package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class SimplePreparedStatementCreator
		implements PreparedStatementCreator {

	private PreparedStatementSetter preparedStatementSetter;

	private String sql;

	public SimplePreparedStatementCreator(
			String sql,
			PreparedStatementSetter preparedStatementSetter) {
		this.sql = sql;
		this.preparedStatementSetter = preparedStatementSetter;
	}

	public PreparedStatement createPreparedStatement(Connection connection)
			throws SQLException {
		PreparedStatement ps = connection
				.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

		preparedStatementSetter.setValues(ps);

		return ps;
	}

}
