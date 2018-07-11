package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.TitularDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class TitularDaoImpl extends AbstractDaoImpl<TitularEntity>
		implements TitularDao {

	private static final Logger LOGGER = LogManager
			.getLogger(TitularDaoImpl.class);

	public TitularDaoImpl() throws DaoException {
		super();
	}

	public TitularEntity findByCpf(String cpf) throws DaoException {
		TitularEntity TitularEntity;
		Query query;
		StringBuilder sb;

		try {
			LOGGER.info("BEGIN");
			sb = new StringBuilder();
			sb.append("select entity from TitularEntity entity ");
			sb.append("join fetch entity.dependentes dependentes ");
			sb.append("where entity.cpf = :cpf ");

			query = createQuery(sb.toString());
			query.setParameter("cpf", cpf);

			TitularEntity = (TitularEntity) query.getSingleResult();

			LOGGER.info("END");
			return TitularEntity;
		} catch (NoResultException e) {
			LOGGER.info(e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<TitularEntity> listByEmpresaId(Long id) throws DaoException {
		List<TitularEntity> titularEntities;
		Query query;
		StringBuilder sb;

		try {
			LOGGER.info("BEGIN");
			sb = new StringBuilder();
			sb.append("select titular from TitularEntity titular ");
			sb.append("join fetch titular.empresa empresa ");
			sb.append("where empresa.id = :idEmpresa ");

			query = createQuery(sb.toString());
			query.setParameter("idEmpresa", id);

			titularEntities = query.getResultList();

			LOGGER.info("END");
			return titularEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
