package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraConditionalFieldDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditionalField;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraConditionalFieldEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.RegraConditionalFieldEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.RegraConditionalFieldUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalFieldUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class RegraConditionalFieldServiceImpl extends
		AbstractServiceImpl<RegraConditionalFieldUi, RegraConditionalFieldEntity, RegraConditionalField> {

	private static final Logger LOGGER = LogManager
			.getLogger(RegraConditionalFieldServiceImpl.class);

	@Autowired
	private RegraConditionalFieldDao regraConditionalFieldDao;

	@Autowired
	private RegraConditionalFieldEntityMapper entityMapper;

	@Autowired
	private RegraConditionalFieldUiMapper uiMapper;

	public RegraConditionalFieldServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected RegraConditionalFieldUi createUi() {
		return new RegraConditionalFieldUi();
	}

	@Override
	protected RegraConditionalFieldEntity createEntity() {
		return new RegraConditionalFieldEntity();
	}

	@Override
	protected AbstractDao<RegraConditionalFieldEntity> getDao() {
		return regraConditionalFieldDao;
	}

	@Override
	protected AbstractMapper<RegraConditionalField, RegraConditionalFieldUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<RegraConditionalField, RegraConditionalFieldEntity> getEntityMapper() {
		return entityMapper;
	}

}
