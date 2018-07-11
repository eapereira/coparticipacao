package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoInputOutputDesconhecidoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputOutputDesconhecidoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class ArquivoInputOutputDesconhecidoDaoImpl
		extends AbstractDaoImpl<ArquivoInputOutputDesconhecidoEntity>
		implements ArquivoInputOutputDesconhecidoDao {

	private static final Logger LOGGER = LogManager
			.getLogger(ArquivoInputOutputDesconhecidoDaoImpl.class);

	public ArquivoInputOutputDesconhecidoDaoImpl() throws DaoException {
		super();
	}

	public List<ArquivoInputOutputDesconhecidoEntity> listByArquivoInputId(
			Long id) throws DaoException {
		List<ArquivoInputOutputDesconhecidoEntity> arquivoInputOutputDesconhecidoEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery2("listByArquivoInputId");
			query.setParameter("id", id);

			arquivoInputOutputDesconhecidoEntities = query.getResultList();

			LOGGER.info("END");
			return arquivoInputOutputDesconhecidoEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
