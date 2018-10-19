package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoInputSheetColsDefDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputSheetColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputSheetColsDefEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ArquivoInputSheetColsDefEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ArquivoInputSheetColsDefUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoInputSheetColsDefService;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Service
public class ArquivoInputSheetColsDefServiceImpl extends
		AbstractServiceImpl<ArquivoInputSheetColsDefUi, ArquivoInputSheetColsDefEntity, ArquivoInputSheetColsDef>
		implements ArquivoInputSheetColsDefService {

	private static final Logger LOGGER = LogManager.getLogger(ArquivoInputSheetColsDefServiceImpl.class);

	@Autowired
	private ArquivoInputSheetColsDefUiMapper uiMapper;

	@Autowired
	private ArquivoInputSheetColsDefEntityMapper entityMapper;

	@Autowired
	private ArquivoInputSheetColsDefDao arquivoInputSheetColsDefDao;

	@Override
	protected ArquivoInputSheetColsDefUi createUi() {
		return new ArquivoInputSheetColsDefUi();
	}

	@Override
	protected ArquivoInputSheetColsDefEntity createEntity() {
		return new ArquivoInputSheetColsDefEntity();
	}

	@Override
	protected AbstractDao<ArquivoInputSheetColsDefEntity> getDao() {
		return arquivoInputSheetColsDefDao;
	}

	@Override
	protected AbstractMapper<ArquivoInputSheetColsDef, ArquivoInputSheetColsDefUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<ArquivoInputSheetColsDef, ArquivoInputSheetColsDefEntity> getEntityMapper() {
		return entityMapper;
	}
}
