package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.UserRoleDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UserRole;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.UserRoleEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.UserRoleEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.UserRoleUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.UserRoleUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.UserRoleService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class UserRoleServiceImpl extends AbstractServiceImpl<UserRoleUi, UserRoleEntity, UserRole>
		implements UserRoleService {

	private static final Logger LOGGER = LogManager.getLogger(UserRoleServiceImpl.class);

	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private UserRoleUiMapper uiMapper;

	@Autowired
	private UserRoleEntityMapper entityMapper;

	@Override
	protected UserRoleUi createUi() {
		return new UserRoleUi();
	}

	@Override
	protected UserRoleEntity createEntity() {
		return new UserRoleEntity();
	}

	@Override
	protected AbstractDao<UserRoleEntity> getDao() {
		return userRoleDao;
	}

	@Override
	protected AbstractMapper<UserRole, UserRoleUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<UserRole, UserRoleEntity> getEntityMapper() {
		return entityMapper;
	}
}
