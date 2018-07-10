package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.LancamentoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class LancamentoDaoImpl extends AbstractDaoImpl<LancamentoEntity>
		implements LancamentoDao {

	private static final Logger LOGGER = LogManager
			.getLogger(LancamentoDaoImpl.class);

	public void deleteByMesAndAno(int mes, int ano) throws DaoException {
		Query query;
		StringBuilder sb;

		try {
			LOGGER.info("BEGIN");

			sb = new StringBuilder();
			sb.append("delete from LancamentoEntity lancamento ");
			sb.append("where	lancamento.mes = :mes ");
			sb.append("and		lancamento.ano = :ano ");

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
