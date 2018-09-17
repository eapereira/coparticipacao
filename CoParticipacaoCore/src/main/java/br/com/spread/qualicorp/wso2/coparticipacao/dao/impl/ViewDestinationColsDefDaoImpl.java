package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ViewDestinationColsDefDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ViewDestinationColsDefEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class ViewDestinationColsDefDaoImpl extends AbstractDaoImpl<ViewDestinationColsDefEntity> implements ViewDestinationColsDefDao {

	private static final Logger LOGGER = LogManager.getLogger(ViewDestinationColsDefDaoImpl.class);

	public ViewDestinationColsDefDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<ViewDestinationColsDefEntity> listByViewDestinationId(Long id) throws DaoException {
		List<ViewDestinationColsDefEntity> viewDestinationColsDefEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByViewDestinationId");
			query.setParameter("viewDestinationId", id);

			viewDestinationColsDefEntities = query.getResultList();

			LOGGER.info("END");
			return viewDestinationColsDefEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
