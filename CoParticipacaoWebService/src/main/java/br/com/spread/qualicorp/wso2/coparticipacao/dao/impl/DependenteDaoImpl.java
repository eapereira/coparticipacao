package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DependenteDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class DependenteDaoImpl extends AbstractDaoImpl<DependenteEntity>
		implements DependenteDao {

	private static final Logger LOGGER = LogManager
			.getLogger(DependenteDaoImpl.class);

	public DependenteDaoImpl() throws DaoException {
		super();
	}

	public DependenteEntity findByCpf(String cpf) throws DaoException {
		DependenteEntity dependenteEntity;
		Query query;
		StringBuilder sb;

		try {
			LOGGER.info("BEGIN");
			sb = new StringBuilder();
			sb.append("select entity from DependenteEntity entity ");
			sb.append("join fetch entity.titular titular ");
			sb.append("where entity.cpf = :cpf ");

			query = createQuery(sb.toString());
			query.setParameter("cpf", cpf);

			dependenteEntity = (DependenteEntity) query.getSingleResult();

			LOGGER.info("END");
			return dependenteEntity;
		} catch (NoResultException e) {
			LOGGER.info(e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<DependenteEntity> listByEmpresaId(Long id) throws DaoException {
		List<DependenteEntity> dependenteEntities;
		Query query;
		StringBuilder sb;

		try {
			LOGGER.info("BEGIN");
			sb = new StringBuilder();
			sb.append("select dependente from DependenteEntity dependente ");
			sb.append("join fetch dependente.titular titular ");
			sb.append("join fetch titular.empresa empresa ");
			sb.append("where empresa.id = :idEmpresa ");

			query = createQuery(sb.toString());
			query.setParameter("idEmpresa", id);

			dependenteEntities = query.getResultList();

			LOGGER.info("END");
			return dependenteEntities;
		} catch (NoResultException e) {
			LOGGER.info(e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
