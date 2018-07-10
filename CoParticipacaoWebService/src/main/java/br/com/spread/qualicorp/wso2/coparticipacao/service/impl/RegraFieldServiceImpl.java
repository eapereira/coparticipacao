package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraFieldDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraField;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraFieldEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.RegraFieldEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.RegraFieldUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraFieldUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Service
public class RegraFieldServiceImpl extends
		AbstractServiceImpl<RegraFieldUi, RegraFieldEntity, RegraField> {

	private static final Logger LOGGER = LogManager
			.getLogger(RegraFieldServiceImpl.class);

	@Autowired
	private RegraFieldDao regraFieldDao;

	@Autowired
	private RegraFieldUiMapper uiMapper;

	@Autowired
	private RegraFieldEntityMapper entityMapper;

	@Override
	protected AbstractDao<RegraFieldEntity> getDao() {
		return regraFieldDao;
	}

	@Override
	protected RegraFieldUi createUi() {
		return new RegraFieldUi();
	}

	@Override
	protected RegraFieldEntity createEntity() {
		return new RegraFieldEntity();
	}

	@Override
	protected AbstractMapper<RegraField, RegraFieldUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<RegraField, RegraFieldEntity> getEntityMapper() {
		return entityMapper;
	}

}
