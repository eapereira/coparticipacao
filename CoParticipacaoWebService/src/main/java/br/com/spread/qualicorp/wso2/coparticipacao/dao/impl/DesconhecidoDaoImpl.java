package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DesconhecidoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DesconhecidoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class DesconhecidoDaoImpl extends AbstractDaoImpl<DesconhecidoEntity>
		implements DesconhecidoDao {

	private static final Logger LOGGER = LogManager
			.getLogger(DesconhecidoDaoImpl.class);

	public DesconhecidoDaoImpl() throws DaoException {
		super();
	}

	public void deleteByMesAndAno(Long arquivoInputId, int mes, int ano)
			throws DaoException {
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery2("deleteByMesAndAno");
			query.setParameter("contratoId", arquivoInputId);
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			query.executeUpdate();

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}

	}

	public List<DesconhecidoEntity> listByMesAndAno(
			Long contratoId,
			int mes,
			int ano) throws DaoException {
		Query query;
		List<DesconhecidoEntity> desconhecidoEntities;

		try {
			LOGGER.info("BEGIN");

			query = createQuery2("listByMesAndAno");
			query.setParameter("contratoId", contratoId);
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			desconhecidoEntities = query.getResultList();

			LOGGER.info("END");
			return desconhecidoEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
