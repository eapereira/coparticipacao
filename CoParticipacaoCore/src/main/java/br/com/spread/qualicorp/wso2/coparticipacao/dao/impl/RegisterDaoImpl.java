package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegisterDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegisterEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class RegisterDaoImpl extends AbstractDaoImpl<RegisterEntity> implements RegisterDao {

	private static final Logger LOGGER = LogManager.getLogger(RegisterDaoImpl.class);

	public RegisterDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<RegisterEntity> listByArquivoInputSheetId(Long arquivoInputSheetId) throws DaoException {
		List<RegisterEntity> registerEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByArquivoInputSheetId");
			query.setParameter("arquivoInputSheetId", arquivoInputSheetId);

			registerEntities = query.getResultList();

			LOGGER.info("END");
			return registerEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
