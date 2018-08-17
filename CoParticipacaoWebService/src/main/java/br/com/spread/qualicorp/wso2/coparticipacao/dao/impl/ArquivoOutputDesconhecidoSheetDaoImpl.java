package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoOutputDesconhecidoSheetDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputDesconhecidoSheetEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class ArquivoOutputDesconhecidoSheetDaoImpl extends AbstractDaoImpl<ArquivoOutputDesconhecidoSheetEntity>
		implements ArquivoOutputDesconhecidoSheetDao {

	private static final Logger LOGGER = LogManager.getLogger(AbstractDaoImpl.class);

	public ArquivoOutputDesconhecidoSheetDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<ArquivoOutputDesconhecidoSheetEntity> listByArquivoInputId(Long arquivoInputId) throws DaoException {
		List<ArquivoOutputDesconhecidoSheetEntity> arquivoOutputDesconhecidoSheetEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByArquivoInputId");
			query.setParameter("arquivoInputId", arquivoInputId);

			arquivoOutputDesconhecidoSheetEntities = query.getResultList();

			LOGGER.info("END");
			return arquivoOutputDesconhecidoSheetEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

}
