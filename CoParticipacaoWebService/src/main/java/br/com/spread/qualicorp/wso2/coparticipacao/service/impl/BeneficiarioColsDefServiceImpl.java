package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.BeneficiarioColsDefDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.BeneficiarioColsDefEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.BeneficiarioColsDefEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.BeneficiarioColsDefUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioColsDefUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class BeneficiarioColsDefServiceImpl extends
		AbstractServiceImpl<BeneficiarioColsDefUi, BeneficiarioColsDefEntity, BeneficiarioColsDef> {

	private static final Logger LOGGER = LogManager
			.getLogger(BeneficiarioColsDefServiceImpl.class);

	@Autowired
	private BeneficiarioColsDefDao beneficiarioColsDefDao;

	@Autowired
	private BeneficiarioColsDefUiMapper uiMapper;

	@Autowired
	private BeneficiarioColsDefEntityMapper entityMapper;

	@Override
	protected BeneficiarioColsDefUi createUi() {
		return new BeneficiarioColsDefUi();
	}

	@Override
	protected BeneficiarioColsDefEntity createEntity() {
		return new BeneficiarioColsDefEntity();
	}

	@Override
	protected AbstractDao<BeneficiarioColsDefEntity> getDao() {
		return beneficiarioColsDefDao;
	}

	@Override
	protected AbstractMapper<BeneficiarioColsDef, BeneficiarioColsDefUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<BeneficiarioColsDef, BeneficiarioColsDefEntity> getEntityMapper() {
		return entityMapper;
	}

}
