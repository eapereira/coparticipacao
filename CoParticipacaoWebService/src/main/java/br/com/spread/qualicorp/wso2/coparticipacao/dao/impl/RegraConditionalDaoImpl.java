package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraConditionalDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraConditionalEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class RegraConditionalDaoImpl extends
		AbstractDaoImpl<RegraConditionalEntity> implements RegraConditionalDao {

	private static final Logger LOGGER = LogManager
			.getLogger(RegraConditionalDaoImpl.class);

	public RegraConditionalDaoImpl() throws DaoException {
		super();
	}

	public List<RegraConditionalEntity> listRegrasByArquivoInput(Long id)
			throws DaoException {
		List<RegraConditionalEntity> regraConditionalEntities;
		Query query;
		StringBuilder sb;

		try {
			LOGGER.info("BEGIN");

			sb = new StringBuilder();
			sb.append(
					"select regraConditional from RegraConditionalEntity regraConditional ");
			sb.append("join fetch regraConditional.arquivoInput arquivoInput ");
			sb.append("where arquivoInput.id = :id ");
			sb.append("order by regraConditional.ordem ");

			query = createQuery(sb.toString());
			query.setParameter("id", id);

			regraConditionalEntities = query.getResultList();

			LOGGER.info("END");
			return regraConditionalEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
