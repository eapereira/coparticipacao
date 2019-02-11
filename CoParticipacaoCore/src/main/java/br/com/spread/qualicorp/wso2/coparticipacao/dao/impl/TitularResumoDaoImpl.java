package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.TitularResumoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularResumoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class TitularResumoDaoImpl extends AbstractDaoImpl<TitularResumoEntity> implements TitularResumoDao {

	private static final Logger LOGGER = LogManager.getLogger(TitularResumoDaoImpl.class);

	public TitularResumoDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<TitularResumoEntity> listByEmpresaId(Long empresaId) throws DaoException {
		Query query;
		List<TitularResumoEntity> titularResumoEntities;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByEmpresaId");
			query.setParameter("empresaId", empresaId);

			titularResumoEntities = query.getResultList();

			LOGGER.info("END");
			return titularResumoEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

}
