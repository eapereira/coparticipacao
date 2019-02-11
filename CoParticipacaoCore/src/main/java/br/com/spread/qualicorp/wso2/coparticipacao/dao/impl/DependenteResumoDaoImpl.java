package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DependenteResumoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteResumoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class DependenteResumoDaoImpl extends AbstractDaoImpl<DependenteResumoEntity> implements DependenteResumoDao {

	private static final Logger LOGGER = LogManager.getLogger(DependenteResumoDaoImpl.class);

	public DependenteResumoDaoImpl() throws DaoException {
		super();
	}

	public List<DependenteResumoEntity> listByEmpresaId(Long empresaId) throws DaoException {
		Query query;
		List<DependenteResumoEntity> dependenteResumoEntities;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByEmpresaId");
			query.setParameter("empresaId", empresaId);

			dependenteResumoEntities = query.getResultList();

			LOGGER.info("END");
			return dependenteResumoEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}
}
