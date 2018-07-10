package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ParameterDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Parameter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ParameterEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ParameterEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ParameterUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ParameterUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ParameterService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class ParameterServiceImpl
		extends AbstractServiceImpl<ParameterUi, ParameterEntity, Parameter>
		implements ParameterService {

	private static final Logger LOGGER = LogManager
			.getLogger(ParameterServiceImpl.class);

	@Autowired
	private ParameterDao parameterDao;

	@Autowired
	private ParameterUiMapper uiMapper;

	@Autowired
	private ParameterEntityMapper entityMapper;

	public ParameterServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ParameterUi createUi() {
		return new ParameterUi();
	}

	@Override
	protected ParameterEntity createEntity() {
		return new ParameterEntity();
	}

	@Override
	protected AbstractDao<ParameterEntity> getDao() {
		return parameterDao;
	}

	@Override
	protected AbstractMapper<Parameter, ParameterUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<Parameter, ParameterEntity> getEntityMapper() {
		return entityMapper;
	}

	public List<ParameterUi> listByEmpresaId(Long id) throws ServiceException {
		List<ParameterUi> parameterUis;

		try {
			LOGGER.info("BEGIN");

			parameterUis = entityToUi(parameterDao.listByEmpresaId(id));

			LOGGER.info("END");
			return parameterUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
