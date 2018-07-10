package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.UserDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.User;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.UserEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.UserEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.UserUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.UserUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.UserService;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Service
public class UserServiceImpl extends
		AbstractServiceImpl<UserUi, UserEntity, User> implements UserService {

	private static final Logger LOGGER = LogManager
			.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserUiMapper uiMapper;

	@Autowired
	private UserEntityMapper entityMapper;

	@Override
	protected AbstractDao<UserEntity> getDao() {
		return userDao;
	}

	@Override
	protected UserUi createUi() {
		return new UserUi();
	}

	@Override
	protected UserEntity createEntity() {
		return new UserEntity();
	}

	@Override
	protected AbstractMapper<User, UserUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<User, UserEntity> getEntityMapper() {
		return entityMapper;
	}

	public UserUi findByNameLogin(String name) throws ServiceException {
		UserUi userUi;

		try {
			LOGGER.info("BEGIN");
			userUi = entityToUi(userDao.findByLogin(name));

			LOGGER.info("END");
			return userUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
