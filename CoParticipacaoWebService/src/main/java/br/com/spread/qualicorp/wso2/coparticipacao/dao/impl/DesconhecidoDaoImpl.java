package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

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

	public DesconhecidoDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public void deleteByMesAndAno(int mes, int ano) throws DaoException {
		Query query;
		StringBuilder sb;

		try {
			LOGGER.info("BEGIN");

			sb = new StringBuilder();
			sb.append("delete from DesconhecidoEntity desconhecido ");
			sb.append("where	desconhecido.mes = :mes ");
			sb.append("and		desconhecido.ano = :ano ");

			query = createQuery(sb.toString());
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			query.executeUpdate();

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}

	}

}
