package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ReportDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ReportEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class ReportDaoImpl extends AbstractDaoImpl<ReportEntity> implements ReportDao {

	private static final Logger LOGGER = LogManager.getLogger(RegraValorDaoImpl.class);

	public ReportDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<ReportEntity> listByEmpresaId(Long empresaId) throws DaoException {
		List<ReportEntity> reportEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByEmpresaId");
			query.setParameter("empresaId", empresaId);

			reportEntities = query.getResultList();

			LOGGER.info("END");
			return reportEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
