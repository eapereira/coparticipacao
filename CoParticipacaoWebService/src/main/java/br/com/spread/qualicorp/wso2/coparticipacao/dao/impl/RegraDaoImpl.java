package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class RegraDaoImpl extends AbstractDaoImpl<RegraEntity>
		implements RegraDao {

	private static final Logger LOGGER = LogManager
			.getLogger(RegraDaoImpl.class);

	public List<RegraEntity> listRegrasByArquivoInput(Long id)
			throws DaoException {
		List<RegraEntity> regraEntities;
		Query query;
		StringBuilder sb;

		try {
			LOGGER.info("BEGIN");

			sb = new StringBuilder();
			sb.append("select regra from RegraEntity regra ");
			sb.append("join fetch regra.arquivoInput arquivoInput ");
			sb.append("where arquivoInput.id = :id ");
			sb.append("order by regra.ordem ");

			query = createQuery(sb.toString());
			query.setParameter("id", id);

			regraEntities = query.getResultList();

			LOGGER.info("END");
			return regraEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
