package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RoleDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.impl.UserRoleDaoImpl;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Role;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RoleEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.RoleEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.RoleUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RoleUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RoleService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class RoleServiceImpl extends AbstractServiceImpl<RoleUi, RoleEntity, Role> implements RoleService {

	private static final Logger LOGGER = LogManager.getLogger(RoleServiceImpl.class);
	
	@Autowired
	private RoleDao roleDao;

	@Autowired
	private RoleUiMapper uiMapper;

	@Autowired
	private RoleEntityMapper entityMapper;

	public RoleServiceImpl() {
		super();
	}

	@Override
	protected RoleUi createUi() {
		return new RoleUi();
	}

	@Override
	protected RoleEntity createEntity() {
		return new RoleEntity();
	}

	@Override
	protected AbstractDao<RoleEntity> getDao() {
		return roleDao;
	}

	@Override
	protected AbstractMapper<Role, RoleUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<Role, RoleEntity> getEntityMapper() {
		return entityMapper;
	}
}
