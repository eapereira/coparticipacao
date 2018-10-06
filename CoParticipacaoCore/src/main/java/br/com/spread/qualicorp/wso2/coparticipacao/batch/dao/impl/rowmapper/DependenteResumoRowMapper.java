package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteResumoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class DependenteResumoRowMapper implements RowMapper<DependenteResumoEntity> {

	private static final int COL_ID = 1;
	private static final int COL_NR_MATRICULA = 2;
	private static final int COL_NR_CPF = 3;
	private static final int COL_NM_DEPENDENTE = 4;

	public DependenteResumoEntity mapRow(ResultSet rs, int rownum) throws SQLException {
		DependenteResumoEntity dependenteResumoEntity = new DependenteResumoEntity();

		dependenteResumoEntity.setId(rs.getLong(COL_ID));
		dependenteResumoEntity.setMatricula(rs.getLong(COL_NR_MATRICULA));
		dependenteResumoEntity.setCpf(rs.getLong(COL_NR_CPF));
		dependenteResumoEntity.setNameBeneficiario(rs.getString(COL_NM_DEPENDENTE));

		return dependenteResumoEntity;
	}

}
