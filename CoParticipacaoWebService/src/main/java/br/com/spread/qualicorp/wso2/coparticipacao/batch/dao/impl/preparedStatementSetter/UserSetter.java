package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.UserEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class UserSetter extends PreparedStatementSetterAdapter<UserEntity> {

	private static final int COL_NM_LOGIN = 1;
	private static final int COL_DESCR_NAME = 2;
	private static final int COL_DESCR_PASSWD = 3;
	private static final int COL_TP_STATUS = 4;
	private static final int COL_USER_CREATED = 5;
	private static final int COL_USER_ALTERED = 5;

	public UserSetter(SetterAdapterType setterAdapterType, UserEntity entity) {
		super(setterAdapterType, entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps) throws SQLException {
		ps.setString(COL_NM_LOGIN, getEntity().getNameLogin());
		ps.setString(COL_DESCR_NAME, getEntity().getDescrName());
		ps.setString(COL_DESCR_PASSWD, getEntity().getPasswd());
		ps.setInt(COL_TP_STATUS, getEntity().getTpStatus().getId());
		ps.setLong(COL_USER_CREATED, getEntity().getUserCreated().getId());
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps) throws SQLException {
		ps.setString(COL_NM_LOGIN, getEntity().getNameLogin());
		ps.setString(COL_DESCR_NAME, getEntity().getDescrName());
		ps.setString(COL_DESCR_PASSWD, getEntity().getPasswd());
		ps.setInt(COL_TP_STATUS, getEntity().getTpStatus().getId());
		ps.setLong(COL_USER_ALTERED, getEntity().getUserAltered().getId());
	}

}
