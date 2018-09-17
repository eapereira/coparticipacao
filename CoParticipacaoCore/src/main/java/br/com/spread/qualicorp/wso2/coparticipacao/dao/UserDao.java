package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.UserEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface UserDao extends AbstractDao<UserEntity> {

	UserEntity findByLogin(String name) throws DaoException;

}
