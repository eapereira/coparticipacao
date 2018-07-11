package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoOutputDesconhecidoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputDesconhecidoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class ArquivoOutputDesconhecidoDaoImpl
		extends AbstractDaoImpl<ArquivoOutputDesconhecidoEntity>
		implements ArquivoOutputDesconhecidoDao {

	private static final Logger LOGGER = LogManager
			.getLogger(ArquivoOutputDesconhecidoDaoImpl.class);

	public ArquivoOutputDesconhecidoDaoImpl() throws DaoException {
		super();
	}

	public ArquivoOutputDesconhecidoEntity findByArquivoInputId(Long id)
			throws DaoException {
		ArquivoOutputDesconhecidoEntity arquivoOutputDesconhecidoEntity;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery2("findByArquivoInputId");
			query.setParameter("id", id);

			arquivoOutputDesconhecidoEntity = (ArquivoOutputDesconhecidoEntity) query
					.getSingleResult();

			LOGGER.info("END");
			return arquivoOutputDesconhecidoEntity;
		} catch (NoResultException e) {
			LOGGER.info(e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
