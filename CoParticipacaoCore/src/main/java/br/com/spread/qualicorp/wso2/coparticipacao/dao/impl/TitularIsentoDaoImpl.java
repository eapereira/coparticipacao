package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.TitularIsentoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularIsentoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class TitularIsentoDaoImpl extends AbstractDaoImpl<TitularIsentoEntity> implements TitularIsentoDao {

	private static final Logger LOGGER = LogManager.getLogger(TitularIsentoDaoImpl.class);

	public TitularIsentoDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public void deleteByMesAndAno(Long empresaId, Integer mes, Integer ano) throws DaoException {
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("deleteByMesAndAno");
			query.setParameter("empresaId", empresaId);
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			query.executeUpdate();
			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public List<TitularIsentoEntity> listByEmpresaId(Long empresaId) throws DaoException {
		List<TitularIsentoEntity> titularIsentoEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByEmpresaId");
			query.setParameter("empresaId", empresaId);

			titularIsentoEntities = query.getResultList();

			LOGGER.info("END");
			return titularIsentoEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public void deleteByContratoAndMesAndAno(Long contratoId, Integer mes, Integer ano) throws DaoException {
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("deleteByContratoAndMesAndAno");
			query.setParameter("contratoId", contratoId);
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			query.executeUpdate();
			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

}
