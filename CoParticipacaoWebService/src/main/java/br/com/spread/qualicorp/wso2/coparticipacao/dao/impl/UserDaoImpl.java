package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.UserDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.UserEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class UserDaoImpl extends AbstractDaoImpl<UserEntity>
		implements UserDao {

	private static final Logger LOGGER = LogManager
			.getLogger(UserDaoImpl.class);

	public UserDaoImpl() throws DaoException {
		super();
	}

	public UserEntity findByLogin(String nameLogin) throws DaoException {
		UserEntity userEntity;
		Query query;
		StringBuilder sb;

		try {
			LOGGER.info("BEGIN");
			sb = new StringBuilder();
			sb.append("select user from UserEntity user ");
			sb.append("where user.nameLogin = :nameLogin ");

			query = createQuery(sb.toString());
			query.setParameter("nameLogin", nameLogin);

			userEntity = (UserEntity) query.getSingleResult();

			LOGGER.info("END");
			return userEntity;
		} catch (NoResultException e) {
			LOGGER.info(e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}
