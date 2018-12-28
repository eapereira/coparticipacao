package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.math.BigDecimal;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoInputSheetColsUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface LancamentoDetailService {

	void setFieldValue(LancamentoDetailUi lancamentoDetailUi, LancamentoColType lancamentoColType, Object value)
			throws ServiceException;

	Object getFieldValue(LancamentoDetailUi lancamentoDetailUi, LancamentoColType lancamentoColType)
			throws ServiceException;

	BigDecimal getFieldValueAsBigDecimal(
			LancamentoInputSheetColsUi lancamentoInputSheetColsUi,
			LancamentoDetailUi lancamentoDetailUi) throws ServiceException;

	LancamentoInputSheetColsUi findByArquivoInputSheetColsDefId(
			CoParticipacaoContext coParticipacaoContext,
			ArquivoInputSheetColsDefUi arquivoInputColsDef) throws ServiceException;

	void showLancamentoDetailInfo(LancamentoDetailUi lancamentoDetailUi) throws ServiceException;

	LancamentoDetailUi create(CoParticipacaoContext coParticipacaoContext) throws ServiceException;
}
