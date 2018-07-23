package br.com.spread.qualicorp.wso2.coparticipacao.jdbc;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface AbstractJdbcDao<ENTITY extends AbstractDomain> {
	Long save(List<ENTITY> entity) throws DaoException;

	Long save(ENTITY entity) throws DaoException;

	Integer delete(List<ENTITY> entity) throws DaoException;

	void delete(ENTITY entity) throws DaoException;
}
