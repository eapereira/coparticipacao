package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DependenteIsentoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteIsentoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class DependenteIsentoDaoImpl extends AbstractDaoImpl<DependenteIsentoEntity> implements DependenteIsentoDao {

	private static final Logger LOGGER = LogManager.getLogger(DependenteIsentoDaoImpl.class);

	public DependenteIsentoDaoImpl() throws DaoException {
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

	public List<DependenteIsentoEntity> listByEmpresaId(Long empresaId) throws DaoException {
		Query query;
		List<DependenteIsentoEntity> dependenteIsentoEntities;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByEmpresaId");
			query.setParameter("empresaId", empresaId);

			dependenteIsentoEntities = query.getResultList();

			LOGGER.info("END");
			return dependenteIsentoEntities;
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
