package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.DynamicDao;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.DynamicSetter;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter.SetterAdapterType;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.rowmapper.DynamicEntityRowMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DynamicEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class DynamicDaoImpl extends AbstractBatchDaoImpl<DynamicEntity> implements DynamicDao {

	private static final Logger LOGGER = LogManager.getLogger(DynamicDaoImpl.class);

	public DynamicDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatementSetter getPreparedStatementSetter(
			DynamicEntity entity,
			SetterAdapterType setterAdapterType) throws DaoException {
		return new DynamicSetter(setterAdapterType, entity);
	}

	public List<DynamicEntity> listByEmpresaAndMesAndAno(String sql, Long contratoId, int mes, int ano)
			throws DaoException {
		List<DynamicEntity> dynamicEntities;
		DynamicEntity dynamicEntity;

		try {
			LOGGER.info("BEGIN");

			dynamicEntity = new DynamicEntity();
			dynamicEntity.addColumn("ID_CONTRATO", contratoId);
			dynamicEntity.addColumn("CD_MES", mes);
			dynamicEntity.addColumn("CD_ANO", ano);

			LOGGER.debug("Executing dynamic query for DynamicEntity [{}]:", sql);

			dynamicEntities = querySql(sql, dynamicEntity, new DynamicEntityRowMapper());

			LOGGER.info("END");
			return dynamicEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public List<DynamicEntity> listByMesAndAno(String sql, int mes, int ano) throws DaoException {
		List<DynamicEntity> dynamicEntities;
		DynamicEntity dynamicEntity;

		try {
			LOGGER.info("BEGIN");

			dynamicEntity = new DynamicEntity();
			dynamicEntity.addColumn("CD_MES", mes);
			dynamicEntity.addColumn("CD_ANO", ano);

			LOGGER.debug("Executing dynamic query for DynamicEntity [{}]:", sql);

			dynamicEntities = querySql(sql, dynamicEntity, new DynamicEntityRowMapper());

			LOGGER.info("END");
			return dynamicEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

}
