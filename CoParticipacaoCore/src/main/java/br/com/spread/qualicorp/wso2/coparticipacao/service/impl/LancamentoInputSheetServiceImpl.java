package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.LancamentoInputSheetDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoInputSheetEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.LancamentoInputSheetEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.LancamentoInputSheetUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoInputSheetService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class LancamentoInputSheetServiceImpl
		extends AbstractServiceImpl<LancamentoInputSheetUi, LancamentoInputSheetEntity, LancamentoInputSheet>
		implements LancamentoInputSheetService {

	@Autowired
	private LancamentoInputSheetDao lancamentoInputSheetDao;

	@Autowired
	private LancamentoInputSheetUiMapper uiMapper;

	@Autowired
	private LancamentoInputSheetEntityMapper entityMapper;

	@Override
	protected LancamentoInputSheetUi createUi() {
		return new LancamentoInputSheetUi();
	}

	@Override
	protected LancamentoInputSheetEntity createEntity() {
		return new LancamentoInputSheetEntity();
	}

	@Override
	protected AbstractDao<LancamentoInputSheetEntity> getDao() {
		return lancamentoInputSheetDao;
	}

	@Override
	protected AbstractMapper<LancamentoInputSheet, LancamentoInputSheetUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<LancamentoInputSheet, LancamentoInputSheetEntity> getEntityMapper() {
		return entityMapper;
	}
}
