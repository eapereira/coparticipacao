package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.LancamentoDetailDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoDetailEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class LancamentoDetailDaoImpl extends AbstractDaoImpl<LancamentoDetailEntity> implements LancamentoDetailDao {

	private static final Logger LOGGER = LogManager.getLogger(LancamentoDetailDaoImpl.class);

	public LancamentoDetailDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<LancamentoDetailEntity> listByEmpresaId(Long empresaId) throws DaoException {
		List<LancamentoDetailEntity> lancamentoDetailEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByEmpresaId");
			query.setParameter("empresaId", empresaId);

			lancamentoDetailEntities = query.getResultList();

			LOGGER.info("END");
			return lancamentoDetailEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
