package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface AbstractDao<ENTITY extends AbstractDomain> {
	String TRANSACTION_MANAGER = "jpaTransactionManager";

	ENTITY findById(Long id) throws DaoException;

	List<ENTITY> listAll() throws DaoException;

	void delete(ENTITY entity) throws DaoException;

	ENTITY save(ENTITY entity) throws DaoException;

	void save(List<ENTITY> entities) throws DaoException;
}
