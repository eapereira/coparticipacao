package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularResumoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class TitularResumoRowMapper implements RowMapper<TitularResumoEntity> {

	private static final int COL_ID = 1;
	private static final int COL_NR_MATRICULA = 2;
	private static final int COL_NR_CPF = 3;

	public TitularResumoEntity mapRow(ResultSet rs, int rownum) throws SQLException {
		TitularResumoEntity titularResumoEntity = new TitularResumoEntity();

		titularResumoEntity.setId(rs.getLong(COL_ID));
		titularResumoEntity.setMatricula(rs.getLong(COL_NR_MATRICULA));
		titularResumoEntity.setCpf(rs.getLong(COL_NR_CPF));

		return titularResumoEntity;
	}

}
