package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.LancamentoInputSheetDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoInputSheetEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class LancamentoInputSheetDaoImpl extends AbstractDaoImpl<LancamentoInputSheetEntity>
		implements LancamentoInputSheetDao {

	private static final Logger LOGGER = LogManager.getLogger(LancamentoInputSheetDaoImpl.class);

	public LancamentoInputSheetDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<LancamentoInputSheetEntity> listByArquivoInputId(Long arquivoInputId) throws DaoException {
		List<LancamentoInputSheetEntity> lancamentoInputSheetEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByArquivoInputId");
			query.setParameter("arquivoInputId", arquivoInputId);

			lancamentoInputSheetEntities = query.getResultList();

			LOGGER.info("END");
			return lancamentoInputSheetEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	public LancamentoInputSheetEntity listByArquivoInputSheetId(Long arquivoInputSheetId) throws DaoException {
		LancamentoInputSheetEntity lancamentoInputSheetEntity;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByArquivoInputSheetId");
			query.setParameter("arquivoInputSheetId", arquivoInputSheetId);

			lancamentoInputSheetEntity = (LancamentoInputSheetEntity) query.getSingleResult();

			LOGGER.info("END");
			return lancamentoInputSheetEntity;
		} catch (NoResultException e) {
			LOGGER.info(e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
