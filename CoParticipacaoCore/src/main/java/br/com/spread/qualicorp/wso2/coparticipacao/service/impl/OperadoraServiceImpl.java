package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.OperadoraDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Operadora;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.OperadoraEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.OperadoraEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.OperadoraUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.OperadoraUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.OperadoraService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class OperadoraServiceImpl extends AbstractServiceImpl<OperadoraUi, OperadoraEntity, Operadora>
		implements OperadoraService {

	private static final Logger LOGGER = LogManager.getLogger(OperadoraServiceImpl.class);

	@Autowired
	private OperadoraDao operadoraDao;

	@Autowired
	private OperadoraUiMapper uiMapper;

	@Autowired
	private OperadoraEntityMapper entityMapper;

	@Override
	protected OperadoraUi createUi() {
		return new OperadoraUi();
	}

	@Override
	protected OperadoraEntity createEntity() {
		return new OperadoraEntity();
	}

	@Override
	protected AbstractDao<OperadoraEntity> getDao() {
		return operadoraDao;
	}

	@Override
	protected AbstractMapper<Operadora, OperadoraUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<Operadora, OperadoraEntity> getEntityMapper() {
		return entityMapper;
	}

}
