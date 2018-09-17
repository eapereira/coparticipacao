package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoOutputDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class ArquivoOutputDaoImpl extends AbstractDaoImpl<ArquivoOutputEntity> implements ArquivoOutputDao {

	private static final Logger LOGGER = LogManager.getLogger(ArquivoOutputDaoImpl.class);

	public ArquivoOutputDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArquivoOutputEntity findByArquivoInputId(Long arquivoInputId, ArquivoType arquivoType) throws DaoException {
		ArquivoOutputEntity arquivoOutputEntity;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("findByArquivoInputId");
			query.setParameter("arquivoInputId", arquivoInputId);
			query.setParameter("arquivoType", arquivoType);

			arquivoOutputEntity = (ArquivoOutputEntity) query.getSingleResult();

			LOGGER.info("END");
			return arquivoOutputEntity;
		} catch (NoResultException e) {
			LOGGER.info(e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public List<ArquivoOutputEntity> listByEmpresaIdAndUseType(Long empresaId, UseType useType) throws DaoException {
		List<ArquivoOutputEntity> arquivoOutputEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByEmpresaIdAndUseType");
			query.setParameter("empresaId", empresaId);
			query.setParameter("useType", useType);

			arquivoOutputEntities = query.getResultList();

			LOGGER.info("END");
			return arquivoOutputEntities;
		} catch (NoResultException e) {
			LOGGER.info(e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

}
