package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.IsentoInputSheetDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.IsentoInputSheetEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class IsentoInputSheetDaoImpl extends AbstractDaoImpl<IsentoInputSheetEntity> implements IsentoInputSheetDao {

	private static final Logger LOGGER = LogManager.getLogger(IsentoInputSheetDaoImpl.class);

	public IsentoInputSheetDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<IsentoInputSheetEntity> listByArquivoInputId(Long arquivoInputId) throws DaoException {
		List<IsentoInputSheetEntity> isentoInputSheetEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByArquivoInputId");
			query.setParameter("arquivoInputId", arquivoInputId);

			isentoInputSheetEntities = query.getResultList();

			LOGGER.info("END");
			return isentoInputSheetEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

}
