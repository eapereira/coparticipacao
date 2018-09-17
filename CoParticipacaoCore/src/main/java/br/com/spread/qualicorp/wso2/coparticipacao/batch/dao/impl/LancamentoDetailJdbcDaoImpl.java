package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.LancamentoDetailJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.LancamentoDetailSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.PreparedStatementSetterByContratoAndMesAndAno;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.SetterAdapterType;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoDetailEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class LancamentoDetailJdbcDaoImpl extends AbstractBatchDaoImpl<LancamentoDetailEntity>
		implements LancamentoDetailJdbcDao {

	private static final Logger LOGGER = LogManager.getLogger(LancamentoDetailJdbcDaoImpl.class);

	public LancamentoDetailJdbcDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			LancamentoDetailEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new LancamentoDetailSetter(setterAdapterType, entity);
	}

	public void deleteByContratoAndMesAndAno(Long contratoId, int mes, int ano) throws DaoException {
		try {
			LOGGER.info("BEGIN");

			update(
					"deleteByContratoAndMesAndAno",
					new PreparedStatementSetterByContratoAndMesAndAno(contratoId, mes, ano));

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

}
