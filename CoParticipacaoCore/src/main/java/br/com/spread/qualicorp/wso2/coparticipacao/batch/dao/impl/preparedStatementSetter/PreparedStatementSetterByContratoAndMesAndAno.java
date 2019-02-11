package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementSetter;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class PreparedStatementSetterByContratoAndMesAndAno implements PreparedStatementSetter {

	private static final int COL_ID_CONTRATO = 1;

	private static final int COL_MES = 2;

	private static final int COL_ANO = 3;

	private Long contratoId;

	private int mes;

	private int ano;

	public PreparedStatementSetterByContratoAndMesAndAno(Long contratoId, int mes, int ano) {
		this.contratoId = contratoId;
		this.mes = mes;
		this.ano = ano;
	}

	public void setValues(PreparedStatement ps) throws SQLException {
		ps.setLong(COL_ID_CONTRATO, contratoId);
		ps.setInt(COL_MES, mes);
		ps.setInt(COL_ANO, ano);
	}

}
